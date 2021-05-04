package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.util.Environment;

public class BoolExpNode extends Node{

    private final Node child;

    public BoolExpNode(int row, int column, Node child) {
        super(row, column);
        this.child = child;
    }
    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type typeCheck() {
        // TODO Auto-generated method stub
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
