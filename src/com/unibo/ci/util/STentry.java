package com.unibo.ci.util;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;

public class STentry extends Entry<Type> {
    private boolean isPar;

    public STentry(String id, Type type, int nestLevel, int offset) {
        super(id, type, nestLevel, offset);
        isPar = false;
    }

    public String toPrint(String indent) {
        return indent + "STentry:\n" + indent + "\tNesting Level: " + nestLevel + "\n" + indent + "\tId: \"" + this.id
                + "\"\n" + type.toPrint(indent + "\t") + indent + "\tOffset: \"" + this.offset + "\"\n";
    }

    public Type getType() {
        return super.type;
    }

    @Override
    public boolean isNotFunction() {
        return type instanceof TypeFunction ? true : false;
    }

    public boolean getIsPar(){
        return this.isPar;
    }

    public void setIsPar(boolean isPar){
        this.isPar = isPar;
    }
}