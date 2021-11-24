package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

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
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        return new ArrayList<SemanticError>();
    }

    @Override
    public TypePointer typeCheck() {
        if (!(type instanceof TypeInt || type instanceof TypeBool || type instanceof TypePointer)) {
            TypeErrorsStorage.add(
                new TypeError(super.row, super.column, "expecting type [" + (new TypeBool()).getTypeName() + "] or [" + (new TypeInt()).getTypeName() + "], found [" + 
                    type.getTypeName() + "]"));
            return null;
        }
        return new TypePointer(type);
    }

    @Override
    public String codeGeneration() {
        return "li $t1 -1\n" + 
                "sw $t1 0($hp) \n";
    }

	@Override
	public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        return new ArrayList<EffectError>();
	}
    
}
