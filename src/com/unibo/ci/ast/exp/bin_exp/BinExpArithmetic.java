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

public class BinExpArithmetic extends BinExp {

    public BinExpArithmetic(Exp right, Exp left) {
        super(right, left);
    }

    @Override
    public BoolExpNode typeCheck() {
        if(! (left.typeCheck() instanceof ValExpNode &&
                right.typeCheck() instanceof ValExpNode)) {
            ErrorStorage.add(
                new TypeError(0, 0, "uff")
            );
            
        }
        return new TypeBool(); ///BoolExpNode();
    }

    ==========================
    @Override
	public final Type inferType() {
		leftType = this.leftSide.inferType();
		Type rightSideT = this.rightSide.inferType();

		if (!leftType.getType().equalsTo(rightSideT))
			TypeErrorsStorage.addError(
					new TypeError("In condition \"" + this.getOperationSymbol() + "\", left expression's type ("
							+ leftType + ") does not equal to the right's type (" + rightSideT + ")", line, column));

		if (EType.VOID.equalsTo(leftType))
			TypeErrorsStorage.addError(new TypeError(
					"Expressions must not be \"void\" type in operation \"" + this.getOperationSymbol() + "\"", line,
					column));
                // return null

		return EType.BOOL.getType();
	}
    ==========================

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
