package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.util.ErrorStorage;
import com.unibo.ci.ast.errors.TypeError;

public class BinExpBool extends BinExpNode {

    public BinExpBool(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }
        

    @Override
    public TypeBool typeCheck() {
        if(! (super.left.typeCheck() instanceof TypeBool &&
                super.right.typeCheck() instanceof TypeBool)) {
            ErrorStorage.add(
                new TypeError(super.row, super.column, "Expecting an boolean value")
            );
            
        }
        return new TypeBool(); ///BoolExpNode();
    
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> leftErrors = super.left.checkSemantics(env);
        ArrayList<SemanticError> rightErrors = super.right.checkSemantics(env);
        leftErrors.addAll(rightErrors);
        return leftErrors;
    }


    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
