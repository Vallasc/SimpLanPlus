package com.unibo.ci.ast.types;

import java.util.List;

import com.unibo.ci.ast.dec.Arg;

public class TypeFunction extends Type{

	private Type returnType;
	private List<Arg> arguments;
	private String labelEndFun = null;
	private String labelStartFun = null;
	
	public TypeFunction(int row, int column, String typeName, int dimension, Type returnType, List<Arg> arguments) {
		super(row, column, "FUNCTION", dimension);
		this.returnType = returnType;
		this.arguments = arguments;
	}
	
	public List<Arg> getArguments(){
		return this.arguments;
	}
	
	public Type getReturnType() {
		return this.returnType;
	}

	public void setLabelEndFun(String label){
		labelEndFun = label;
	}

	public String getLabelEndFun(){
		return labelEndFun;
	}

	public void setLabelStartFun(String label){
		labelStartFun = label;
	}

	public String getLabelStartFun(){
		return labelStartFun;
	}

    @Override
    public boolean equals(Type e) {
		if (e == null)
			return false;
		if(e instanceof TypeFunction){
			TypeFunction o = (TypeFunction)e;
			return  o.returnType.equals(returnType) && o.arguments.equals(arguments);
		}
		return false;
	}
	
	
}
