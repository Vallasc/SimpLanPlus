package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.*;
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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }
    
    @Override
    public Type typeCheck() {
        return new TypeInt();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
