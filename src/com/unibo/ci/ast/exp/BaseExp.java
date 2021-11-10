package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.ast.errors.EffectError;
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
        return indent + "Exp: Base\n" + child.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        return child.checkSemantics(env);
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
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        return child.AnalyzeEffect(env);
    }
    
}
