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

    public  abstract boolean equals(Object e);

    // Metodi Node

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type typeCheck() {
        // Ritorna null perché non serve per i tipi
        return null;
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
