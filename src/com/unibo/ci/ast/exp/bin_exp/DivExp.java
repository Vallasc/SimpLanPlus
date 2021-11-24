package com.unibo.ci.ast.exp.bin_exp;

import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeInt;

public class DivExp extends BinExp {

    public DivExp(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public TypeInt typeCheck() {
        if (!(super.left.typeCheck() instanceof TypeInt && super.right.typeCheck() instanceof TypeInt)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "expecting an integer value"));
            return null;
        }
        return new TypeInt();
    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;
        
        String out = (debug ? ";BEGIN " + 	this.toPrint("") + "\n" : "");        out += left.codeGeneration();
        out += "push $a0" + (debug ? " ; push on the stack e1\n" : "\n");
        out += right.codeGeneration();
        out += "lw $t1 0($sp)" + (debug ? " ;$t1 = e1, $a0 = e2\n" : "\n");
        out +="pop" + (debug ? " ;pop e1 from the stack\n" : "\n");

        out +="div $a0 $t1 $a0\n";

        out += (debug ? ";END \n" : "");
        return out;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Div\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
