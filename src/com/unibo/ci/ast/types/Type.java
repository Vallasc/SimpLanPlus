package com.unibo.ci.ast.types;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.util.Environment;

public abstract class Type extends Node{

    protected final String TYPE_NAME;
    protected final int DIMENSION;

    public Type(int row, int column, String typeName, int dimension){
        super(row, column);
        this.TYPE_NAME = typeName;
        this.DIMENSION = dimension;
        
    }

    public int getDimension(){
        return DIMENSION;
    }

    public String getTypeName(){
        return TYPE_NAME;
    }

    public abstract boolean equals(Type e);

    // Metodi Node

    @Override
    public String toPrint(String indent) {
        return indent + "Type: " + TYPE_NAME + "\n";
    }

    @Override
    public Type typeCheck() {
        return this;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
}
