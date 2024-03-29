package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.errors.Warning;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.util.WarningsStorage;

public class DeleteStmt extends Statement {

    private String id;
    private STentry stEntry;
    private int nestingLevel;

    public DeleteStmt(int row, int column, String id) {
        super(row, column);
        this.id = id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        stEntry = env.lookup(id);
        nestingLevel = env.getNestingLevel();
        if (stEntry == null) {
            errors.add(new SemanticError(super.column, super.row,
                    "cannot delete variable " + id + ", cause it is not declared."));
        }
        return errors;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Stmt: delete \"" + id + "\"\n";
    }

    @Override
    public Type typeCheck() {

        if (stEntry == null) {
            return null;
        }

        if (!(stEntry.getType() instanceof TypePointer)) {
            TypeErrorsStorage.add(new TypeError(super.column, super.row, "Variable type must be [POINTER[*]]"));
        }
        return stEntry.getType();

    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN DELETE [" + id + "]\n" : "");
        out += "mv $al $fp \n";

        for (int i = 0; i < nestingLevel - stEntry.getNestinglevel(); i++) {
            out += "lw $al 0($al)\n";
        }

        int offset = stEntry.getOffset() - 1;
        out += "lw $a0 " + offset + "($al)\n";

        out += "del $a0\n";
        out += (debug ? ";END DELETE [" + id + "]\n" : "");
        return out;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        if (!stEntry.getIsPar() && (env.lookup(id).getEtype() == EffectHelper.ETypes.BOT
                                                                || !stEntry.isInitFlag()) ) {
            WarningsStorage.add(new Warning(row, column, "uninitialized variable [" + id + "]"));
        }

        ArrayList<EffectError> toRet = new ArrayList<EffectError>();
        env.lookup(id).updateEffectType(EffectHelper.seq(env.lookup(id).getEtype(), EffectHelper.ETypes.D));

        if (env.lookup(id).getEtype().equals(EffectHelper.ETypes.T)) {
            toRet.add(new EffectError(row, column,
                    "Cannot delete variable " + id + ": the variable has already been deleted"));

        }

        return toRet;
    }

}
