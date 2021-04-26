package com.unibo.ci.ast.types;

public class TypePointer implements Type {
    private static final String TYPE_NAME = "POINTER";
    private static final int DIMENSION = 4;

    private final Type pointedType;


    public TypePointer(TypePointer pointedType) {
        this.pointedType = pointedType;
    }

    public TypePointer(TypeInt pointedType) {
        this.pointedType = pointedType;
    }

    public TypePointer(TypeBool pointedType) {
        this.pointedType = pointedType;
    }

    public Type getPointedType() {
        return pointedType;
    }

    @Override
    public int getDimension() {
        return DIMENSION;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }


    // Puntatori tutti uguali
    @Override
    public boolean equals(Object e) { 
		if (e == null)
			return false;
        if (e instanceof TypePointer){
            return true;
        }
        return false;
	}

    /*@Override
    public boolean equals(Object e) { 
		if (e == null)
			return false;
        if (e instanceof TypePointer){
            TypePointer casted = (TypePointer) e;
            return getPointedType().equals(casted.getPointedType());
        }
        return false;
	}*/
}