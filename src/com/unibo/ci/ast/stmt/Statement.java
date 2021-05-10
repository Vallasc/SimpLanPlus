package com.unibo.ci.ast.stmt;

import com.unibo.ci.ast.Node;

public abstract class Statement extends Node {
    public Statement(int row, int column) {
        super(row, column);
    }
}