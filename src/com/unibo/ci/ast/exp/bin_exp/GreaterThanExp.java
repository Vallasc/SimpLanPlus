package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.LabelManager;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;

public class GreaterThanExp extends BinExp {

    public GreaterThanExp(int row, int column, Exp right, Exp left) {
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

        String out = (debug ? ";BEGIN GREATER THAN\n" : "\n");
        out += left.codeGeneration();
        out += "push $a0" + (debug ? " ;push on the stack e1\n" : "\n");
        out += right.codeGeneration();
        out += "lw $t1 0($sp)" + (debug ? " ;$t1 = e1, $a0 = e2\n" : "\n");
        out += "pop" + (debug ? " ;pop e1 from the stack\n" : "\n");

        String trueBranchLabel = LabelManager.getInstance().newLabel("greaterTrueBranch");
        String endCheckLabel = "end" + trueBranchLabel;

        out += "bleq $t1 $a0 " + trueBranchLabel + "\n";
        // False branch
        out += "li $a0 1\n";
        out += "b " + endCheckLabel + "\n";
        out += trueBranchLabel + ":\n";
        out += "li $a0 0\n";
        out += endCheckLabel + ":\n";

        out += (debug ? ";END GREATER THAN\n" : "\n");
        return out;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: GreaterThan\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
