package com.unibo.ci.ast.exp;

public abstract class LhsExp  extends Exp {

    public LhsExp(int row, int column) {
        super(row, column);
    }

    public abstract String getVarId();

}
