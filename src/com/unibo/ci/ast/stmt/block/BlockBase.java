package com.unibo.ci.ast.stmt.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.unibo.ci.ast.stmt.Statement;
import com.unibo.ci.ast.dec.Dec;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;

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
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		env.newScope();
		
		declarations.forEach(dec -> {
			errors.addAll(dec.checkSemantics(env));
		});
		statements.forEach(stmt -> {
			errors.addAll(stmt.checkSemantics(env));
		});
		System.out.println(env.toPrint(""));
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
		statements.forEach(stmt -> {
			stmt.typeCheck();
		});
		return new TypeVoid();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

}
