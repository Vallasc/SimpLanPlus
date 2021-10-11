package com.unibo.ci.ast.types;

import java.util.ArrayList;

public class TypeFunction extends Type {

    private final Type returnType;
    private final ArrayList<Type> parTypes;

    public TypeFunction(Type returnType, ArrayList<Type> parTypes) {
        super(-1, -1, "FUNCTION", 0);
        this.returnType = returnType;
        this.parTypes = parTypes;
    }

    public ArrayList<Type> getParTypes() {
        return parTypes;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public boolean equals(Object e) {
        if (e == null)
            return false;
        return (e instanceof TypeFunction);
    }
}
