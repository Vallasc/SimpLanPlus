package com.unibo.ci.ast.errors;

public class EffectError extends Error{

	public EffectError(int row, int col, String desc) {
		super(row, col, desc);
		// TODO Auto-generated constructor stub
	}
	
	String print(){
        return "Effect error on ["+ row + "," + col + "] : " + desc;
    }

}
