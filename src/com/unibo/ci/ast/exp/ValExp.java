package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;

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
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        return new ArrayList<SemanticError>();
    }
    
    @Override
    public TypeInt typeCheck() {
        return new TypeInt();
    }

    @Override
    public String codeGeneration() {
        return "li $a0 " + value +"\n";
    }

	@Override
	public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
		return new ArrayList<EffectError>();
	}
    
}
