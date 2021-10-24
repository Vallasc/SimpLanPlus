package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

public class ReturnStmt extends Statement {

    private Exp exp;

    public ReturnStmt(int row, int column, Exp exp) {
        super(row, column);
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Return: \n" + 
                (exp != null ? exp.toPrint(indent + "\t") : indent + "\tvoid");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        if(exp != null)
            return exp.checkSemantics(env);
        else 
            return new ArrayList<SemanticError>();
    }

    @Override
    public Type typeCheck() {
        if (exp == null) 
            return new TypeVoid();

        Type returnType = exp.typeCheck();

        if(returnType == null)
            return null;

        // controllare che il tipo sia int o bool o void
        if(returnType instanceof TypeInt || returnType instanceof TypeBool)
            return returnType;

        TypeErrorsStorage.add( new TypeError(this.exp.getRow(), this.exp.getColumn(), 
                                    "Incompatible return type (" + returnType + ")"));
        return null;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

}
