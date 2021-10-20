package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeInt;

public class GreaterThanExp extends BinExp {

    public GreaterThanExp(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public TypeInt typeCheck() {
        if (!(super.left.typeCheck() instanceof TypeInt && super.right.typeCheck() instanceof TypeInt)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "Expecting an integer value"));
            return null;
        }
        return new TypeInt();
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
        return indent + "Exp: GreaterThan\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
