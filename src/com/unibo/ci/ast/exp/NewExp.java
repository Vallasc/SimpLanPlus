package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;

public class NewExp extends Exp {
    final Type type;

    public NewExp(int row, int column, Type type) {
        super(row, column);
        this.type = type;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: New\n" + type.toPrint(indent + "\t");
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
