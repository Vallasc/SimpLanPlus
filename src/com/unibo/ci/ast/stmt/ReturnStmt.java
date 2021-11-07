package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.TypeErrorsStorage;

// tipo ritorno = tipo funzione

public class ReturnStmt extends Statement {
    private Exp exp;
    private STentry stEntry;
    
    public ReturnStmt(int row, int column, Exp exp) {
        super(row, column);
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Stmt: return\n" + 
                (exp != null ? exp.toPrint(indent) : "");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        stEntry = env.lookupFunction();
        if(exp != null)
            errors.addAll(exp.checkSemantics(env));
        return errors;
    }

    @Override
    public Type typeCheck() {
        Type functionType;
        if(stEntry == null)
            functionType = new TypeVoid();
        else
            functionType = ((TypeFunction) stEntry.getType()).getReturnType();
        Type returnType;
        if(exp == null)
            returnType = new TypeVoid();
        else
            returnType = exp.typeCheck();

        if(! functionType.equals(returnType)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "Return type must be [" + functionType.getTypeName() + "]"));
        }
        return returnType;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(Environment env) {
        // TODO Auto-generated method stub
        return null;
    }

}
