package com.unibo.ci.ast.errors;

public class TypeError extends Error {

    public TypeError(int col, int row, String desc) {
        super(col, row, desc);
    }   
    
    String print(){
        return "Type error on ["+ row + "," + col + "] : " + desc;
    }
}
