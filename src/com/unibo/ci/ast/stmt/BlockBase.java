package com.unibo.ci.ast.stmt;

import java.util.ArrayList;
import java.util.List;

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
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeFunction;

/**
 * Example: { }
 */
public class BlockBase extends Statement {
	private final List<Dec> declarations;
	private final List<Statement> statements;

	private boolean isMain = false;
	private boolean isFunctionBlock = false;
	private TypeFunction typeFunction = null;

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
		STentry functionStEntry = env.lookupFunction();
		if(functionStEntry != null)
			this.typeFunction = (TypeFunction) functionStEntry.getType();
		statements.forEach(stmt -> {
			errors.addAll(stmt.checkSemantics(env));
		});

		env.exitScope();

		return errors;
	}

	public ArrayList<SemanticError> checkSemanticsInjectArgs(GammaEnv env, List<Arg> args) {
		this.isFunctionBlock = true;
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		env.newScope();
		args.forEach(arg -> {
			errors.addAll(arg.checkSemantics(env));
		});
		env.newScope();
		declarations.forEach(dec -> {
			errors.addAll(dec.checkSemantics(env));
		});
		STentry functionStEntry = env.lookupFunction();
		if(functionStEntry != null)
			this.typeFunction = (TypeFunction) functionStEntry.getType();
		statements.forEach(stmt -> {
			errors.addAll(stmt.checkSemantics(env));
		});
		env.exitScope();
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
			if (type != null && (stmt instanceof IteStmt || stmt instanceof ReturnStmt || stmt instanceof BlockBase)) {
				stmtReturn.add(type);
			}
		});

		Type returnType = stmtReturn.stream()
				.reduce(null, (accumulator, element) -> {
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
		out += "push $fp" + (debug ? " ;push old fp\n" : "\n");
		out += "push $cl\n";


		// Pushing ra so the stack is always consistent
		out += "li $t1 0\n";
		out += "push $t1\n";

		out += "mv $al $fp\n";
		out += "push $al" + (debug ? " ;it's equal to the old $fp\n" : "\n");
		out += "mv $fp $sp\n";

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


		boolean flagReturn = false;
		// Generate statements
		for (Statement s : statements) {
			out += s.codeGeneration();
			if(s instanceof ReturnStmt && typeFunction != null){
				out += codeGenEnd(varDecs.size());
				out += "b " + typeFunction.getLabelEndFun() + "\n";
				flagReturn = true;
			}
		}
		if(!flagReturn && isFunctionBlock){
			out += codeGenEnd(varDecs.size());
			out += "b " + typeFunction.getLabelEndFun() + "\n";
		} else {
			out += codeGenEnd(varDecs.size());
		}

		if (isMain){
			out += "halt\n";
		}
		
		// Function declaration at the end, they need the space for ra
		for (DecFun f : funDecs) {
			out += f.codeGeneration();
		}
		out += (debug ? ";END BLOCK\n" : "");

		return out;
	}

	public String codeGenEnd(int varDecSize){
		boolean debug = GlobalConfig.PRINT_COMMENTS;
		// Pop all the declarations
		String out = "addi $sp $sp " + varDecSize + (debug ? " ;pop var declarations\n" : "\n"); // Pop var
		// declarations.
		out += "pop" + (debug ? " ;pop $al\n" : "\n");
		out += "pop" + (debug ? " ;pop consistency ra\n" : "\n");
		out += "lw $cl 0($sp)\n";
		out += "pop\n";
		out += "lw $fp 0($sp)" + (debug ? " ;restore old $fp\n" : "\n");
		out += "pop" + (debug ? " ;pop old $fp\n" : "\n");
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

	public void setTypeFunction(TypeFunction typeFunction) {
		this.typeFunction = typeFunction;
	}

}
