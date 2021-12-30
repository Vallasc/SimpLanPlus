package com.unibo.ci.ast.stmt.block;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.stmt.IteStmt;
import com.unibo.ci.ast.stmt.ReturnStmt;
import com.unibo.ci.ast.stmt.Statement;
import com.unibo.ci.ast.dec.Arg;
import com.unibo.ci.ast.dec.Dec;
import com.unibo.ci.ast.dec.DecFun;
import com.unibo.ci.ast.dec.DecVar;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

/**
 * Example: { }
 */
public class BlockBase extends Block {
	private final List<Dec> declarations;
	private final List<Statement> statements;

	private boolean isMain = false;

	public BlockBase(List<Dec> declarations, List<Statement> statements, int row, int column) {
		super(row, column);
		this.declarations = declarations;
		this.statements = statements;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		env.newScope();

		declarations.forEach(dec -> {
			errors.addAll(dec.checkSemantics(env));
		});
		statements.forEach(stmt -> {
			errors.addAll(stmt.checkSemantics(env));
		});
		// System.out.println(env.toPrint(""));
		env.exitScope();

		return errors;
	}

	public ArrayList<SemanticError> checkSemanticsInjectArgs(GammaEnv env, List<Arg> args) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		env.newScope();
		args.forEach(arg -> {
			errors.addAll(arg.checkSemantics(env));
		});
		declarations.forEach(dec -> {
			errors.addAll(dec.checkSemantics(env));
		});
		statements.forEach(stmt -> {
			errors.addAll(stmt.checkSemantics(env));
		});
		env.exitScope();

		return errors;
	}

	@Override
	public String toPrint(String indent) {
		StringBuilder out = new StringBuilder(indent + "Block\n");
		declarations.forEach(dec -> {
			out.append(dec.toPrint(indent + "\t"));
		});
		statements.forEach(stmt -> {
			out.append(stmt.toPrint(indent + "\t"));
		});
		return out.toString();
	}

	@Override
	public Type typeCheck() {
		declarations.forEach(dec -> {
			dec.typeCheck();
		});

		ArrayList<Type> stmtReturn = new ArrayList<Type>();
		statements.forEach(stmt -> {
			Type type = stmt.typeCheck();
			// System.out.println("DEBUG blockbase: type " + type);
			if (type != null && (stmt instanceof IteStmt || stmt instanceof ReturnStmt || stmt instanceof BlockBase)) {
				stmtReturn.add(type);
			}
		});

		Type returnType = stmtReturn.stream()
				.reduce(null, (accumulator, element) -> {
					// System.out.println("DEBUG blockbase stream: element" + element);
					if (element == null) {
						return accumulator;
					}

					if ((element instanceof TypeInt || element instanceof TypeBool || element instanceof TypeVoid) &&
							(accumulator != null && !accumulator.equals(element))) {
						TypeErrorsStorage.add(new TypeError(row, column, "return type mismatch in block element"));
					}
					return element;
				});

		return returnType;
	}

	@Override
	public String codeGeneration() {
		boolean debug = GlobalConfig.PRINT_COMMENTS;

		String out = (debug ? ";BEGIN BLOCK\n" : "");

		// New scope
		if (isMain) {
			out += "push $sp\n";
		} else {
			out += "push $fp" + (debug ? " ;push old fp\n" : "\n");
			out += "push $cl\n";
		}

		// Pushing ra so the stack is always consistent
		out += "subi $sp $sp 1; ra \n";

		out += "mv $al $fp\n";
		out += "push $al" + (debug ? " ;it's equal to the old $fp\n" : "\n");
		if (isMain) {
			out += "mv $fp $sp" + (debug ? " ;bring up the frame pointer\n" : "\n");
			out += "sw $fp 0($fp)" + (debug ? " ;save the old value\n" : "\n");
		}

		List<DecVar> varDecs = new ArrayList<>();
		List<DecFun> funDecs = new ArrayList<>();

		for (Dec d : declarations) {
			if (d instanceof DecVar)
				varDecs.add((DecVar) d);
			if (d instanceof DecFun)
				funDecs.add((DecFun) d);
		}

		// Generate code for declarations
		for (DecVar d : varDecs){
			out += d.codeGeneration();
		}

		if (!isMain) {
			out += "mv $fp $sp" + (debug ? " ;frame pointer above the new declarations\n" : "\n");
			out += "addi $fp $fp " + varDecs.size()
					+ (debug ? " ;frame pointer before decs (n = " + varDecs.size() + ")\n" : "\n");
		}

		// Generate statements
		for (Statement s : statements) {
			out += s.codeGeneration();
		}
		if (isMain){
			out += "halt\n";
		}


		if (!isMain) {
			// Pop all the declarations
			out += "addi $sp $sp " + varDecs.size() + (debug ? " ;pop var declarations\n" : "\n"); // Pop var
																									// declarations.
			out += "pop" + (debug ? " ;pop $al" : "\n");
			out += "pop" + (debug ? " ;pop consistency ra" : "\n");
			out += "lw $cl 0($sp)\n";
			out += "pop\n";
			out += "lw $fp 0($sp)" + (debug ? " ;restore old $fp" : "\n");
			out += "pop" + (debug ? " ;pop old $fp\n" : "\n");
		}

		// Function declaration at the end, they need the space for ra
		for (DecFun f : funDecs) {
			out += f.codeGeneration();
		}
		out += "; END BLOCK\n";

		return out;
	}

	@Override
	public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
		ArrayList<EffectError> errors = new ArrayList<EffectError>();

		env.newScope();

		errors.addAll(AnalyzeEffectNoScope(env));

		env.exitScope();

		return errors;
	}

	public ArrayList<EffectError> AnalyzeEffectNoScope(SigmaEnv env) { // serve per l'analisi degli effetti nella
																		// dichiarazione di funzione
		ArrayList<EffectError> errors = new ArrayList<EffectError>();

		declarations.forEach(dec -> {

			errors.addAll(dec.AnalyzeEffect(env));
		});

		statements.forEach(stmt -> {
			errors.addAll(stmt.AnalyzeEffect(env));
		});

		return errors;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}
}
