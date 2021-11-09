package com.unibo.ci.ast.stmt.block;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.stmt.CallStmt;
import com.unibo.ci.ast.stmt.IteStmt;
import com.unibo.ci.ast.stmt.ReturnStmt;
import com.unibo.ci.ast.stmt.Statement;
import com.unibo.ci.ast.dec.Arg;
import com.unibo.ci.ast.dec.Dec;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

/**
 * Example: { }
 */
public class BlockBase extends Block {
	final List<Dec> declarations;
	final List<Statement> statements;

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
		//System.out.println(env.toPrint(""));
		env.exitScope();

		return errors;
	}

	public ArrayList<SemanticError> checkSemanticsInjectArgs(Environment env, List<Arg> args) {
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
			//System.out.println("DEBUG blockbase: type " + type);
			if( type != null && (stmt instanceof IteStmt || stmt instanceof ReturnStmt || stmt instanceof BlockBase) ) { 
				stmtReturn.add(type);
			}
		});


		
		Type returnType = stmtReturn.stream()
				.reduce(null, (accumulator, element) -> {
					//System.out.println("DEBUG blockbase stream: element" + element);
					if(element == null){
						return accumulator;
					}

					if((element instanceof TypeInt || element instanceof TypeBool || element instanceof TypeVoid) && 
							(accumulator != null && !accumulator.equals(element))) {
							TypeErrorsStorage.add(new TypeError(row, column, "return type mismatch in block element"));
					} 					
					return element;
				});

		
		return returnType;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
		// TODO Auto-generated method stub
		return null;
	}

}
