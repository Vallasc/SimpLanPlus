package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.Warning;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.WarningsStorage;

public class VarExp extends LhsExp {
    private final String id;
    private STentry stEntry;
    private int nestingLevel;

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
        nestingLevel = env.getNestingLevel();
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
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN ID " + "\n" : "");
        out += "mv $al $fp \n";

        for (int i = 0; i < nestingLevel - stEntry.getNestinglevel(); i++) {
            out += "lw $al 0($al)\n";
        }

        int offset = stEntry.getOffset() - 1;
        out += "\t lw $a0 " + offset + "($al)\n";

        out += (debug ? ";END ID\n" : "");
        return out;
    }

    @Override
    public VarExp getVarId() {
        return this;
    }

    public String getId() {
        return id;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public STentry getSTentry() {
        return stEntry;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        ArrayList<EffectError> toRet = new ArrayList<EffectError>();

        if (env.lookup(id).getEtype() == EffectHelper.ETypes.BOT) {
        	WarningsStorage.add(new Warning(row, column, "uninitialized variable " + "[" + id + "]" + "\n"));
        }
        
        env.lookup(id).updateEffectType(
                EffectHelper.seq(
                        env.lookup(id).getEtype(),
                        EffectHelper.ETypes.RW));
        if (env.lookup(id).getEtype() == EffectHelper.ETypes.T) {
            toRet.add(new EffectError(row, column, "Cannot use variable [" + id + "]: the variable was deleted"));
        }
        
        return toRet;

    }
    
    

}
