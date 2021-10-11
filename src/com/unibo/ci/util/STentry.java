package com.unibo.ci.util;

import com.unibo.ci.ast.types.Type;

public class STentry {

    private int nestLevel;
    private Type type;
    private String id;
    private int offset;
    private boolean deleted;

    public STentry(int nestLevel, int offset, Type type) {
        this.nestLevel = nestLevel;
        this.offset = offset;
        this.type = type;
        this.setDeleted(false);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}