package com.unibo.ci.ast.types;

public class TypeBool extends Type{
    
    public TypeBool(){
        super(-1, -1, "BOOL", 4);
    }
    
    public TypeBool(int row, int column){
        super(row, column, "BOOL", 4);
    }

    @Override
    public boolean equals(Type e) {
		if (e == null)
			return false;
		return (e instanceof TypeBool);
	}

}
