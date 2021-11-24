package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;

public class BoolExp extends Exp {

    private final boolean value;

    public BoolExp(int row, int column, boolean value) {
        super(row, column);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Bool(" + String.valueOf(value) + ")\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        return new ArrayList<SemanticError>();
    }

    @Override
    public Type typeCheck() {
        return new TypeBool();
    }

    @Override
    public String codeGeneration() {
		return "li $a0 " + (this.value ? 1 : 0) +"\n";
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        return new ArrayList<EffectError>();
    }

}
