package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;

public abstract class BinExp implements Exp{

    protected final Exp right;
    protected final Exp left;

    public BinExp(Exp right, Exp left){
        this.right = right;
        this.left = left;
    }

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
