package com.unibo.ci.ast.types;

public class TypeVoid extends Type{

    public TypeVoid(){
        super("VOID", 0);
    }

    @Override
    public boolean equals(Object e) {
		if (e == null)
			return false;
		return (e instanceof TypeVoid);
	}
}
