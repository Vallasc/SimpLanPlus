package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;

public class ValExp extends Exp {

    private final int value;

    public ValExp(int r, int c, int value) {
        super(r, c);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Val(" + String.valueOf(value) + ")\n";
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
        return new ArrayList<SemanticError>();
    }
    
}
