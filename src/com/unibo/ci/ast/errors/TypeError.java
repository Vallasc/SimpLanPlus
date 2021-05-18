package com.unibo.ci.ast.errors;

public class TypeError extends Error {

    public TypeError(int row, int col, String desc) {
        super(row, col, desc);
    }   
    
    String print(){
        return "Type error on ["+ row + "," + col + "] : " + desc;
    }
}
