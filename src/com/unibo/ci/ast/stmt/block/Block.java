package com.unibo.ci.ast.stmt.block;

import com.unibo.ci.ast.stmt.Statement;

public abstract class Block extends Statement {
    public Block(int row, int column) {
        super(row, column);
    }
}