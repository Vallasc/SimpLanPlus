package com.unibo.ci.ast.types;

public class TypeBool extends Type{
    
    public TypeBool(){
        super("BOOL", 4);
    }

    @Override
    public boolean equals(Object e) {
		if (e == null)
			return false;
		return (e instanceof TypeBool);
	}
}
