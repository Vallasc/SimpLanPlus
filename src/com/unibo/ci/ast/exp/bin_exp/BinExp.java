package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.Environment;

public abstract class BinExp extends Exp {

    protected final Exp right;
    protected final Exp left;

    public BinExp(int row, int column, Exp right, Exp left) {
        super(row, column);
        this.right = right;
        this.left = left;
    }

    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> leftErrors = left.checkSemantics(env);
        ArrayList<SemanticError> rightErrors = right.checkSemantics(env);
        leftErrors.addAll(rightErrors);
        return leftErrors;
    }
}
