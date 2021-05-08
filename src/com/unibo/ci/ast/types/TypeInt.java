package com.unibo.ci.ast.types;

public class TypeInt extends Type {

    public TypeInt(){
        super("INT", 4);
    }
    
    @Override
    public boolean equals(Object e) {
		if (e == null)
			return false;
		return (e instanceof TypeInt);
	}
}
