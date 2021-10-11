package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.TypeErrorsStorage;

/**
 * Example: x = 3;
 */
public class AssignmentStmt extends Statement {

	private final String id;
	private final Exp exp;

	private STentry stEntry;

	public AssignmentStmt(int row, int column, String id, Exp exp) {
		super(row, column);
		this.id = id;
		this.exp = exp;
	}

    @Override
    public String toPrint(String indent) {
        return indent + "Assignment:\n" + 
                indent + "\tId: \"" + this.id + "\"\n" +
				this.exp.toPrint(indent + "\t");
    }

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		stEntry = env.lookupSTentry(id);

		if (stEntry == null)
			errors.add(new SemanticError(row, column, "var " + id + " does not exist"));

		errors.addAll(exp.checkSemantics(env));
		return errors;
	}

	@Override
	public Type typeCheck() {
		if(stEntry == null)
			return null;
		Type typeId = stEntry.getType();
		Type typeExp = exp.typeCheck();
		if(!typeId.equals(typeExp)){
			System.err.println(typeId.toPrint(""));
			System.err.println(typeExp.toPrint(""));
			TypeErrorsStorage.add(new TypeError(super.row, super.column, "cannot assign [" + typeExp.getTypeName() + "] to [" + typeId.getTypeName() + "]"));
			return null;
		}
		return typeId;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

}
