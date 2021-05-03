package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;

public class BaseExpNode implements Exp{

    private Exp child;

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
