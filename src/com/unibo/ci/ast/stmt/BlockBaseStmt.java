package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.Block;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;

public class BlockBaseStmt extends Block{

	public BlockBaseStmt(int row, int column) {
		super(row, column);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type typeCheck() {
		return new TypeVoid();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		return new ArrayList<SemanticError>();
	}

}
