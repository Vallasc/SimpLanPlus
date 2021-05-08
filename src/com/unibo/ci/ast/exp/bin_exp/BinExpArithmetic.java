package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.BoolExpNode;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.exp.ValExpNode;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.ErrorStorage;
import com.unibo.ci.ast.types.TypeInt;

public class BinExpArithmetic extends BinExpNode{

    public BinExpArithmetic(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public TypeInt typeCheck() { 
        if(! (super.left.typeCheck() instanceof TypeInt &&
                super.right.typeCheck() instanceof TypeInt)) {
            ErrorStorage.add(
                new TypeError(super.row, super.column, "Expecting an integer value")
            );
            
        }
        return new TypeInt(); ///BoolExpNode();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
