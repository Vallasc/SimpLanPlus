package com.unibo.ci.ast.types;

public class TypePointer extends Type {

    private final Type pointedType;


    public TypePointer(TypePointer pointedType) {
        super("POINTER", 4);
        
        this.pointedType = pointedType; 
    }

    public TypePointer(TypeInt pointedType) {
        super("POINTER", 4);
        
        this.pointedType = pointedType;
    }

    public TypePointer(TypeBool pointedType) {
        super("POINTER", 4);

        this.pointedType = pointedType;
    }

    public Type getPointedType() {
        return pointedType;
    }


    // Puntatori tutti uguali
    /*@Override
    public boolean equals(Object e) { 
		if (e == null)
			return false;
        if (e instanceof TypePointer){
            return true;
        }
        return false;
	}*/

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