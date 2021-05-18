package com.unibo.ci.ast.errors;

public class SemanticError extends Error {

    public SemanticError(int row, int col, String desc) {
        super(row, col, desc);
    }   
    
    String print(){
        return "Semantic error on ["+ row + "," + col + "] : " + desc;
    }
}
