package com.unibo.ci.ast.stmt;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.Types;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.util.EffectHelper.ETypes;

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

        String out = (debug ? ";BEGIN DELETE " + this.toPrint("") + "\n" : "");
        out += "mv $al $fp \n";

        for (int i = 0; i < nestingLevel - stEntry.getNestinglevel(); i++) {
            out += "lw $al 0($al)\n";
        }

        int offset = stEntry.getOffset() - 1;
        out += "\t lw $a0 " + offset + "($al)\n";

        out += "del $a0\n";
        out += (debug ? ";END DELETE\n" : "");
        return out;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {

        ArrayList<EffectError> toRet = new ArrayList<EffectError>();

        env.lookup(id).updateEffectType(EffectHelper.seq(env.lookup(id).getEtype(), EffectHelper.ETypes.D));

        if (env.lookup(id).getEtype().equals(EffectHelper.ETypes.T)) {
            toRet.add(new EffectError(row, column,
                    "Cannot delete variable " + id + ": the variable has already been deleted"));

        }
        System.out.println(env.toPrint("*"));
        /*
         * 
         * statement.forEach(stmt -> stmt.analyzeEffect())
         * 
         * DEBUG: analizzo statement com.unibo.ci.ast.stmt.IteStmt@27ddd392, 3, 8
         * DEBUG: analizzo statement com.unibo.ci.ast.stmt.AssignmentStmt@19e1023e, 6,
         * 12
         * DEBUG: analizzo statement com.unibo.ci.ast.stmt.CallStmt@7cef4e59, 7, 12
         * DEBUG: analizzo statement com.unibo.ci.ast.stmt.DeleteStmt@64b8f8f4, 8, 12
         * DEBUG: analizzo statement com.unibo.ci.ast.stmt.DeleteStmt@2db0f6b2, 4, 12
         */

        return toRet;
    }

}
