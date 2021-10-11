package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.types.*;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

/**
 * Neg Expression
 */
public class NegExp extends Exp {
    
    private final Exp child;

    public NegExp(int row, int column, Exp child) {
        super(row, column);
        this.child = child;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Neg\n" + 
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
                new TypeError(super.row, super.column, "expecting type  [" + (new TypeBool()).getTypeName() + "], found [" + childType.getTypeName() + "]"));
        }
        return child.typeCheck();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
