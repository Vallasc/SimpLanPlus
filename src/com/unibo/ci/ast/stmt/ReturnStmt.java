package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;

// tipo ritorno = tipo funzione

public class ReturnStmt extends Statement {

    private Exp exp;

    public ReturnStmt(int row, int column, Exp exp) {
        super(row, column);
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type typeCheck() {

        return this.exp == null ? new TypeVoid() : this.exp.typeCheck();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if (exp != null) {
            errors.addAll(exp.checkSemantics(env));
        }
        return null;
    }

}
