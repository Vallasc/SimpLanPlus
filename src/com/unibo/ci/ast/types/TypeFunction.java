package com.unibo.ci.ast.types;

public class TypeFunction extends Type{

    public TypeFunction(){
        super("FUNCTION", 0);
    }    

    @Override
    public boolean equals(Object e) {
		if (e == null)
			return false;
		return (e instanceof TypeFunction);
	}
}
