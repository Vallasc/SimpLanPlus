package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.types.*;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

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
        return indent + "Exp: Neg\n" + child.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        return child.checkSemantics(env);
    }

    @Override
    public Type typeCheck() {
        Type childType = child.typeCheck();
        if (!(childType instanceof TypeBool)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "expecting type  ["
                    + (new TypeBool()).getTypeName() + "], found [" + childType.getTypeName() + "]"));
            return null;
        }
        return childType;
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
