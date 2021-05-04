package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;

public class BinExpBool extends BinExpNode {

    public BinExpBool(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public Type typeCheck() {
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
