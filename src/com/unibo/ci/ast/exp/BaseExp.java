package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;

public class BaseExp extends Exp {

    private final Exp child;

    public BaseExp(int row, int column, Exp child) {
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
        return child.typeCheck();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return child.checkSemantics(env);
    }
    
}
