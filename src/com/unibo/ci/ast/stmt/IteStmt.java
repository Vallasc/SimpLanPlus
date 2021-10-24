package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

public class IteStmt extends Statement {

    private final Exp exp;
    private final Statement thenStmt, elseStmt;

    public IteStmt(Exp exp, Statement thenStmt, Statement elseStmt, int row, int column) {
        super(row, column);
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;

    }

    @Override
    public String toPrint(String indent) {
        return indent + "\tIf:\n" + exp.toPrint(indent + "\t") + "\n" +
                indent + "\tThen:\n" + thenStmt.toPrint(indent + "\t") + "\n" +
                (elseStmt != null ? indent + "\tElse:\n" + elseStmt.toPrint(indent) : "");
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

    @Override
    public Type typeCheck() {
        Type expType = exp.typeCheck();
        if (!(expType instanceof TypeBool)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "if condition must be of" + (new TypeBool()).getTypeName()));
            return null;
        }

        Type thenType = thenStmt.typeCheck();
        if(thenStmt instanceof CallStmt)
            thenType = new TypeVoid();

        if(elseStmt != null)
            return thenType;
        
        Type elseType = elseStmt.typeCheck();  
        if(elseStmt instanceof CallStmt)
            elseType = new TypeVoid();

        if(elseType.equals(thenType))
            return thenType;
        
        if(thenType instanceof TypeVoid)
            return elseType;
        if(elseType instanceof TypeVoid)
            return thenType;

        TypeErrorsStorage.add(new TypeError(super.row, super.column, "Type braches mismatch"));
        return null;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

}
