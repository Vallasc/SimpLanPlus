package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.TypeErrorsStorage;

// tipo ritorno = tipo funzione

public class ReturnStmt extends Statement {

    private Exp exp;
    private STentry funStEntry;

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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        exp.checkSemantics(env);

        funStEntry = env.lookupFunction();
        /*
         * Non concesso nel main { return; }
         */
        if (funStEntry == null) {
            errors.add(new SemanticError(super.column, super.row, "No function to return."));
        }
        return errors;
    }

    @Override
    public Type typeCheck() {
        if (funStEntry == null) {
            return null;
        }

        Type returnType = ((TypeFunction) funStEntry.getType());

        if (exp == null && !returnType.equals(new TypeVoid())) {
            TypeErrorsStorage.add(
                    new TypeError(super.column, super.row, "Return type must be [" + returnType.getTypeName() + "]."));
        } else if (!returnType.equals(exp.typeCheck())) {
            TypeErrorsStorage.add(
                    new TypeError(super.column, super.row, "Return type must be [" + returnType.getTypeName() + "]."));
        }
        // controllare che il tipo sia int o bool o void
        return returnType;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

}
