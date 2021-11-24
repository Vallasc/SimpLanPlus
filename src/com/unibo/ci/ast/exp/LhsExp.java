package com.unibo.ci.ast.exp;

public abstract class LhsExp  extends Exp {

    protected boolean assignment = false;

    public LhsExp(int row, int column) {
        super(row, column);
    }

    public abstract VarExp getVarId();

    public void setAssignment(boolean assignment) {
        this.assignment = assignment;
    }

}
