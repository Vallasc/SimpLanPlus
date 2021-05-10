package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.util.Environment;

public class BoolExpNode extends Node{

    private final boolean value;

    public BoolExpNode(int row, int column, boolean value) {
        super(row, column);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type typeCheck() {
        return new TypeBool();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }
    
}
