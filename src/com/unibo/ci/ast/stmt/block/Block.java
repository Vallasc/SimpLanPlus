package com.unibo.ci.ast.stmt.block;

import com.unibo.ci.ast.Node;

public abstract class Block extends Node {
    public Block(int row, int column) {
        super(row, column);
    }
}