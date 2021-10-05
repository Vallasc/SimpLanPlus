package com.unibo.ci.util;

import com.unibo.ci.ast.types.Type;

public class STentry {

    private int nestLevel;
    private Type type;
    private String id;
    private int offset;

    public STentry(int nestLevel, int offset, Type type) {
        this.nestLevel = nestLevel;
        this.offset = offset;
        this.type = type;
    }

    public void addType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public int getNestinglevel() {
        return nestLevel;
    }

    /*
     * public String toPrint(String indent) { return indent + "STentry: nestlev " +
     * Integer.toString(nestLevel +"\n"+ indent + "STentry: type\n" +
     * type.toPrint(indent + "  ") + indent + "STentry: offset " +
     * Integer.toString(offset) + "\n"; }
     */
}