package com.unibo.ci.ast.types;

public class TypeInt extends Type {

    public TypeInt(){
        super(-1, -1, "INT", 4);
    }

    public TypeInt(int row, int column){
        super(row, column, "INT", 4);
    }
    
    @Override
    public boolean equals(Type e) {
		if (e == null)
			return false;
		return (e instanceof TypeInt);
	}
}
