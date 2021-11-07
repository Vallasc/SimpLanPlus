package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.exp.LhsExp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

/**
 * Example: x = 3;
 */
public class AssignmentStmt extends Statement {

	private final LhsExp left;
	private final Exp exp;

	public AssignmentStmt(int row, int column, LhsExp left, Exp exp) {
		super(row, column);
		this.left = left;
		this.exp = exp;
	}

    @Override
    public String toPrint(String indent) {
        return indent + "Assignment:\n" + 
                indent + "\tLeft: \n" + 
				this.left.toPrint(indent + "\t\t") +
				indent + "\tRight: \n" + 
				this.exp.toPrint(indent + "\t\t");
    }

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		errors.addAll(left.checkSemantics(env));
		errors.addAll(exp.checkSemantics(env));
		return errors;
	}

	@Override
	public Type typeCheck() {
		Type typeLeft = left.typeCheck();
		Type typeExp = exp.typeCheck();
		if(typeExp == null)
			return null;

		if(!typeLeft.equals(typeExp)){
			TypeErrorsStorage.add(new TypeError(super.row, super.column, 
				"Cannot assign [" + typeExp.getTypeName() + "] to [" + typeLeft.getTypeName() + "]"));
			return null;
		}
		return new TypeVoid();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EffectError> AnalyzeEffect(Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

}
