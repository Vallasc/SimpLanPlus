package com.unibo.ci.ast.exp.bin_exp;

import com.unibo.ci.ast.exp.Exp;

public abstract class BinExpNode extends Exp {

    protected final Exp right;
    protected final Exp left;

    public BinExpNode(int row, int column, Exp right, Exp left) {   
        super(row, column);
        this.right = right;
        this.left = left;
    }
    
}
