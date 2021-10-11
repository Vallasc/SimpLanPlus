package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

public class NotExp extends Exp {

    private final Exp child;

    public NotExp(int row, int column, Exp child) {
        super(row, column);
        this.child = child;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Not\n" + 
                child.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return child.checkSemantics(env);
    }

    @Override
    public Type typeCheck() {
        Type childType = child.typeCheck();
        if(!(childType instanceof TypeBool)){
            TypeErrorsStorage.add(
                new TypeError(super.row, super.column, "expecting type [" + (new TypeBool()).getTypeName() + "] found [" + childType.getTypeName() + "]"));
        }
        return child.typeCheck();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
