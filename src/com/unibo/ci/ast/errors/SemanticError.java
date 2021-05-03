package com.unibo.ci.ast.errors;

public class SemanticError extends Error {

    public SemanticError(int col, int row, String desc) {
        super(col, row, desc);
    }   
    
    String print(){
        return "Semantic error on ["+ row + "," + col + "] : " + desc;
    }
}
