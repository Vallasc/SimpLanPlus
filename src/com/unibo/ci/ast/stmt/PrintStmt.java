package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;

public class PrintStmt extends Statement {

    private Exp expToPrint;

    public PrintStmt(int row, int column, Exp exp) {
        super(row, column);
        this.expToPrint = exp;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Stmt: print\n" + expToPrint.toPrint(indent);
    }

    @Override
    public Type typeCheck() {
        if (expToPrint != null && expToPrint.typeCheck() instanceof TypeVoid) {
            new TypeError(row, column, "Cannot print void expression");
        }
        return null;
    }

    @Override
    public String codeGeneration() {
        return expToPrint.codeGeneration() + "print $a0\n";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if (expToPrint != null) {
            errors.addAll(expToPrint.checkSemantics(env));
        }
        return errors;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        ArrayList<EffectError> toRet = new ArrayList<EffectError>();
        if (expToPrint != null) {
            toRet.addAll(expToPrint.AnalyzeEffect(env));
        }
        return toRet;
    }

}
