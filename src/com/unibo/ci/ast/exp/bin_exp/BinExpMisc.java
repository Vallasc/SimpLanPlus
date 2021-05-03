package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;

public class BinExpMisc extends BinExp {

    public BinExpMisc(Exp right, Exp left) {
        super(right, left);
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
