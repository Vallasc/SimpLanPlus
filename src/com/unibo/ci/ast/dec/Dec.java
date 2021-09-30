package com.unibo.ci.ast.dec;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.types.Type;

public abstract class Dec extends Node {
    protected final Type type;
    protected final String id;

    public Dec(int row, int column, Type type, String id) {
        super(row, column);
        this.type = type;
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Declaration: \n" + type.toPrint(indent + "\t");
    }
}
