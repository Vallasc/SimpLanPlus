package com.unibo.ci.ast.types;

import java.util.List;

import com.unibo.ci.ast.dec.Arg;

public class TypeFunction extends Type{

	private Type returnType;
	private List<Arg> arguments;
	
	public TypeFunction(int row, int column, String typeName, int dimension, Type returnType, List<Arg> arguments) {
		super(row, column, typeName, dimension);
		this.returnType = returnType;
		this.arguments = arguments;
	}
	
	public List<Arg> getArguments(){
		return this.arguments;
	}

	@Override
	public boolean equals(Type e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
