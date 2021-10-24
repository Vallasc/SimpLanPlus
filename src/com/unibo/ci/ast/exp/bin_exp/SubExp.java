package com.unibo.ci.ast.exp.bin_exp;

import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeInt;

public class SubExp extends BinExp {

    public SubExp(int row, int column, Exp right, Exp left) {
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
    public String toPrint(String indent) {
        return indent + "Exp: Sub\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
