package com.unibo.ci.util;

import com.unibo.ci.ast.types.Type;

public class STentry {

    private final int nestLevel;
    private final Type type;
    private final String id;
    private final int offset;

    public STentry(String id, Type type, int nestLevel, int offset) {
        this.nestLevel = nestLevel;
        this.offset = offset;
        this.type = type;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public String toPrint(String indent) {
        return indent + "STentry:\n" + indent + "\tNesting Level: " + nestLevel + "\n" + indent + "\tId: \"" + this.id
                + "\"\n" + type.toPrint(indent + "\t") + indent + "\tOffset: \"" + this.offset + "\"\n";
    }

}