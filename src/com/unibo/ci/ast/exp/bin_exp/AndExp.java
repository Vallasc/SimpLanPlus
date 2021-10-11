package com.unibo.ci.ast.exp.bin_exp;

import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeBool;

public class AndExp extends BinExp {

    public AndExp(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public TypeBool typeCheck() {
        if (!(super.left.typeCheck() instanceof TypeBool && super.right.typeCheck() instanceof TypeBool)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "expecting an integer value"));
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
        return indent + "Exp: And\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
