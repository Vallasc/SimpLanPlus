package com.unibo.ci.ast.types;

public class TypeVoid extends Type{

    public TypeVoid(){
        super(-1, -1, "VOID", 0);
    }

    @Override
    public boolean equals(Type e) {
		if (e == null)
			return false;
		return (e instanceof TypeVoid);
	}
}
