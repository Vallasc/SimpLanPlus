package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.util.Environment;

/**
 * Neg Expression
 */
public class NegExp extends Exp {
    
    private final Exp child;

    public NegExp(int row, int column, Exp child) {
        super(row, column);
        this.child = child;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Neg\n" + 
                child.toPrint(indent + "\t");
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
        return child.checkSemantics(env);
    }
    
}