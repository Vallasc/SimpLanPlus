package com.unibo.ci.ast.exp;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.SigmaEnv;

public class VarExp extends LhsExp {
    private final String id;
    private STentry stEntry;

    public VarExp(int row, int column, String id) {
        super(row, column);
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Var\n" + indent + "\t Id: " + id + "\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        stEntry = env.lookup(id);
        if (stEntry == null)
            errors.add(new SemanticError(row, column, "var [" + id + "] does not exist"));
        return errors;
    }

    @Override
    public Type typeCheck() {
        if (stEntry == null)
            return null;
        return stEntry.getType();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getVarId() {
        return id;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        ArrayList<EffectError> toRet = new ArrayList<EffectError>();

        env.lookup(id).updateEffectType(EffectHelper.seq(env.lookup(id).getEtype(), EffectHelper.ETypes.RW));
        if (env.lookup(id).getEtype() == EffectHelper.ETypes.T) {
            toRet.add(new EffectError(row, column, "Cannot access variable " + id + ": the variable was deleted"));
        }

        return toRet;

    }

}
