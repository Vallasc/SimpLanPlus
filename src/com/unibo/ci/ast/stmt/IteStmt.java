package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

public class IteStmt extends Statement {

    Exp exp;
    Statement thenStmt, elseStmt;

    public IteStmt(Exp exp, Statement thenStmt, Statement elseStmt, int row, int column) {
        super(row, column);
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;

    }

    @Override
    public String toPrint(String indent) {

        if (elseStmt == null) {
            return indent + "Stmt: If " + exp.toPrint(indent) + " - then " + thenStmt.toPrint(indent) + " \n"
                    + (indent + "\t");

        } else {
            return indent + "Stmt: If " + exp.toPrint(indent) + " then " + thenStmt.toPrint(indent) + " \n"
                    + (indent + "\t") + " else " + elseStmt + " \n" + (indent + "\t");
        }
    }

    @Override
    public Type typeCheck() {
        if(exp == null) return null;
        Type expType = exp.typeCheck();
        Type thenType = exp.typeCheck();
        Type elseType = exp.typeCheck();

        if (!(expType instanceof TypeBool)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "Condition must be " + (new TypeBool()).getTypeName()));
            return null;
        }
        if(thenType == null)
            return null;

        if (elseStmt != null && !thenType.equals(elseType)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "Type braches mismatch"));
            return null;
        }
        return thenType;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(exp.checkSemantics(env));

        errors.addAll(thenStmt.checkSemantics(env));

        if (elseStmt != null) {
            errors.addAll(elseStmt.checkSemantics(env));
        }
        return errors;
    }

}
