package com.unibo.ci.ast.types;

public class TypePointer extends Type {

    private final Type pointedType;

    public TypePointer(Type pointedType) {
        super(-1, -1, "POINTER", 4);
        this.pointedType = pointedType; 
    }

    public TypePointer(int row, int column, Type pointedType) {
        super(row, column, "POINTER", 4);
        this.pointedType = pointedType; 
    }

    public Type getPointedType() {
        return pointedType;
    }

    @Override
    public boolean equals(Object e) { 

		if (e == null)
			return false;
        if (e instanceof TypePointer){
            TypePointer casted = (TypePointer) e;
            return getPointedType().equals(casted.getPointedType());
        }
        return false;
	}
}