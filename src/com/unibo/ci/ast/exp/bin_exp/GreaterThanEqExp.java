package com.unibo.ci.ast.exp.bin_exp;

import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.LabelManager;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;

public class GreaterThanEqExp extends BinExp {

    public GreaterThanEqExp(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public TypeBool typeCheck() {
        if (!(super.left.typeCheck() instanceof TypeInt && super.right.typeCheck() instanceof TypeInt)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "Expecting an integer value"));
            return null;
        }
        return new TypeBool();
    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN GREATER THAN EQUAL\n" : "");
        out += left.codeGeneration();
        out += "push $a0" + (debug ? " ;push on the stack e1\n" : "\n");
        out += right.codeGeneration();
        out += "lw $t1 0($sp)" + (debug ? " ;$t1 = e1, $a0 = e2\n" : "\n");
        out += "pop" + (debug ? " ;pop e1 from the stack\n" : "\n");

        String equalTrueBranch = LabelManager.getInstance().newLabel("equalTrueBranch");
        String endEqualCheck = "end" + equalTrueBranch;
        String lesseqTrueBranch = LabelManager.getInstance().newLabel("lesseqTrueBranch");
        String endLesseqCheck = "end" + lesseqTrueBranch;

        out += "beq $t1 $a0 " + equalTrueBranch + "\n";
        // False branch => e1 != e2
        out += "bleq $t1 $a0 " + lesseqTrueBranch + "\n";
        // InnerFalse branch => e1 > e2
        out += "li $a0 1\n";
        out += "b " + endLesseqCheck + "\n";
        out += lesseqTrueBranch + ":\n";
        out += "li $a0 0\n"; // e1 < e2
        out += endLesseqCheck + ":\n";
        out += "b " + endEqualCheck + "\n";
        out += equalTrueBranch + ":\n";
        out += "li $a0 1\n"; // e1 == e2
        out += endEqualCheck + ":\n";

        out += (debug ? ";END GREATER THAN EQUAL\n" : "");
        return out;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: GreaterThanEq\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
