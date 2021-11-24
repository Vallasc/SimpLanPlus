package com.unibo.ci.ast.exp.bin_exp;

import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.LabelManager;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;

public class NotEqualExp extends BinExp {

    public NotEqualExp(int row, int column, Exp right, Exp left) {
        super(row, column, right, left);
    }

    @Override
    public TypeBool typeCheck() {
        Type leftType = super.left.typeCheck();
        Type rightType = super.right.typeCheck();
        if ((leftType instanceof TypeBool && rightType instanceof TypeInt)
                || (leftType instanceof TypeInt && rightType instanceof TypeBool)) {
            TypeErrorsStorage.add(new TypeError(super.row, super.column, "Type mismatch"));
            return null;
        }
        return new TypeBool();
    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;
        
        String out = (debug ? ";BEGIN " + 	this.toPrint("") + "\n" : "");        out += left.codeGeneration();
        out += "push $a0" + (debug ? " ;push on the stack e1\n" : "\n");
        out += right.codeGeneration();
        out += "lw $t1 0($sp)" + (debug ? " ;$t1 = e1, $a0 = e2\n" : "\n");
        out += "pop" + (debug ? " ;pop e1 from the stack\n" : "\n");

        String trueBranchLabel = LabelManager.getInstance().newLabel("unequalTrueBranch");
        String endCheckLabel = "end" + trueBranchLabel;

        out += "beq $t1 $a0 " +trueBranchLabel+"\n";
        //False branch e1 != e2
        out += "li $a0 1 \n";
        out += "b " + endCheckLabel +"\n";
        out += trueBranchLabel +":\n";
        //e1 == e2
        out += "li $a0 0 \n";
        out += endCheckLabel + ":\n";

        out += (debug ? ";END \n" : "");
        return out;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: NotEq\n" + super.left.toPrint(indent + "\t") + super.right.toPrint(indent + "\t");
    }

}
