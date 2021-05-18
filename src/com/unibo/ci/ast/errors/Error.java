package com.unibo.ci.ast.errors;

public abstract class Error {
    public final int col;
    public final int row;

    public final String desc;

    public Error(int row, int col, String desc) {
        this.col = col;
        this.row = row;
        this.desc = desc;
    }

    String print(){
        return "Generic error on ["+ row + "," + col + "] : " + desc;
    }
    
}
