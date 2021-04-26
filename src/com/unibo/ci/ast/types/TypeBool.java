package com.unibo.ci.ast.types;

public class TypeBool implements Type{
    private static final String TYPE_NAME = "BOOL";
    private static final int DIMENSION = 4;


    @Override
    public int getDimension() {
        return DIMENSION;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    @Override
    public boolean equals(Object e) {
		if (e == null)
			return false;
		return (e instanceof TypeBool);
	}
}
