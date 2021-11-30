package com.unibo.ci.ast.errors;

public class Warning extends Error{

	public Warning(int row, int col, String desc) {
		super(row, col, desc);
	}

}
