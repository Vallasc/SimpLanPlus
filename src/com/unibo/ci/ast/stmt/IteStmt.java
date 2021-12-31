package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.dec.Dec;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.stmt.block.BlockBase;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.LabelManager;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

public class IteStmt extends Statement implements Cloneable {

    private final Exp exp;
    private final Statement thenStmt, elseStmt;

    public IteStmt(Exp exp, Statement thenStmt, Statement elseStmt, int row, int column) {
        super(row, column);
        this.exp = exp;
        if ((thenStmt instanceof BlockBase)) {
            this.thenStmt = thenStmt;
        } else {
            ArrayList<Statement> tmp = new ArrayList<Statement>();
            tmp.add(thenStmt);
            this.thenStmt = new BlockBase(new ArrayList<Dec>(), tmp, thenStmt.getRow(), thenStmt.getColumn());
        }
        if ((elseStmt != null && elseStmt instanceof BlockBase)) {
            this.elseStmt = elseStmt;
        } else if (elseStmt != null) {
            ArrayList<Statement> tmp = new ArrayList<Statement>();
            tmp.add(thenStmt);
            this.elseStmt = new BlockBase(new ArrayList<Dec>(), tmp, elseStmt.getRow(), elseStmt.getColumn());
        } else {
            this.elseStmt = null;
        }

    }

    @Override
    public String toPrint(String indent) {
        return indent + "\tIf:\n" + exp.toPrint(indent + "\t") + "\n" + indent + "\tThen:\n"
                + thenStmt.toPrint(indent + "\t") + "\n"
                + (elseStmt != null ? indent + "\tElse:\n" + elseStmt.toPrint(indent) : "");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(exp.checkSemantics(env));

        errors.addAll(thenStmt.checkSemantics(env));

        if (elseStmt != null) {
            errors.addAll(elseStmt.checkSemantics(env));
        }

        return errors;
    }

    @Override
    public Type typeCheck() {
        Type expType = exp.typeCheck();
        if (!(expType instanceof TypeBool)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column,
                    "If condition must be of [" + (new TypeBool()).getTypeName() + "]"));
        }

        Type thenType = thenStmt.typeCheck();

        // Nessun ramo else
        if (elseStmt == null)
            return null;

        Type elseType = elseStmt.typeCheck();

        // Posso avere solo return, blocchi o altri ite
        if (!(thenStmt instanceof ReturnStmt || thenStmt instanceof BlockBase || thenStmt instanceof IteStmt))
            return null;
        if (!(elseStmt instanceof ReturnStmt || elseStmt instanceof BlockBase || elseStmt instanceof IteStmt))
            return null;

        if (elseType == null || thenType == null)
            return null;

        if (elseType.equals(thenType))
            return thenType;

        TypeErrorsStorage.add(new TypeError(super.row, super.column, "Braches types mismatch"));
        return null;
    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN ITE " + "\n" : "");

        String then = LabelManager.getInstance().newLabel("then");
        String end = LabelManager.getInstance().newLabel("endif");
        out += exp.codeGeneration();
        out += "li $t1 1\n";
        out += "beq $a0 $t1 " + then + "\n";

        if (elseStmt != null) {
            out += (debug ? ";ELSE\n" : "");
            out += elseStmt.codeGeneration();
        }
        out += "\t b " + end + "\n" + "\t" + then + ":\n";
        out += "; THAN\n";
        out += thenStmt.codeGeneration();

        out += end + " :\n";
        out += (debug ? ";END ITE\n" : "");
        return out;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        ArrayList<EffectError> toRet = new ArrayList<EffectError>();

        // System.out.println("DEBUG: ambiente prima di ite_analyze_effect");
        // env.toPrint("indent").toString();

        toRet.addAll(exp.AnalyzeEffect(env));

        analyzeBlockEffect(env, thenStmt, toRet);

        SigmaEnv tempE = null;

        if (elseStmt != null) {

            tempE = (SigmaEnv) env.clone();
            analyzeBlockEffect(tempE, elseStmt, toRet);
        }

        analyzeBlockEffect(env, thenStmt, toRet);
        // env.toPrint("indent").toString();

        if (tempE != null) {
            EffectHelper.maxModifyEnv(env, tempE);
        }

        // System.out.println("DEBUG: ambiente dopo di ite_analyze_effect");
        // env.toPrint("indent").toString();

        return toRet;
    }

    private void analyzeBlockEffect(SigmaEnv e, Statement stmt, ArrayList<EffectError> toRet) {

        if (stmt instanceof BlockBase) {
            toRet.addAll(stmt.AnalyzeEffect(e));
        } else {
            e.newScope();
            toRet.addAll(stmt.AnalyzeEffect(e));
            e.exitScope();
        }
    }

    public static class CloneException extends Exception {

    }
}
