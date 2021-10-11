package com.unibo.ci.ast.exp.bin_exp;

import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;

public class EqualExp extends BinExp {

    public EqualExp(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public TypeBool typeCheck() {
        if ((super.left.typeCheck() instanceof TypeBool && super.right.typeCheck() instanceof TypeInt)
                || (super.left.typeCheck() instanceof TypeInt && super.right.typeCheck() instanceof TypeBool)) {
                    TypeErrorsStorage.add(new TypeError(super.row, super.column, "type mismatch"));
        }
        return new TypeBool();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Equal\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
