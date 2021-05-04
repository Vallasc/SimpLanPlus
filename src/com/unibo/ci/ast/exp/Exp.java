package com.unibo.ci.ast.exp;

import com.unibo.ci.ast.Node;

public abstract class Exp extends Node {
    public Exp(int row, int column) {
        super(row, column);
    }
}
