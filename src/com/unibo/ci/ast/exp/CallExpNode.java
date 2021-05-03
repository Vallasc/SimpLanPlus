package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.STentry;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.util.Environment;

public class CallExpNode implements Node{

    private String id;
    private STentry entry; 
    private ArrayList<Node> parlist; 
    private int nestinglevel;

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node typeCheck() {
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
