package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.ErrorStorage;
import com.unibo.ci.util.STentry;

public class AssignmentStmt extends Statement {

	private Environment env;

	private String id;

	private Exp exp;

	public AssignmentStmt(int row, int column, String id, Exp exp) {
		super(row, column);
		this.id = id;
		this.exp = exp;
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type typeCheck() {

		// Type type1 = env.lookup(id);
		// Type type2 = exp.typeCheck();

		// System.out.println(type1.toPrint(""));
		// System.out.println(type2.toPrint(""));

		// if (type1 == type2 ) {
		// return type1;
		// }

		// TODO continua da qua
		// TODO è corretta?
		// ^int b : puntatore a intero
		// ^^int a : puntatore a puntatore a intero
		// b == a ; no
		// b == a^ ; si

		// a = b ;
		// TP(TP(TI)) = TP(TI)
		/*
		 * if (type1.getClass().equals(TypePointer.class)) { type1 =
		 * ((TypePointer)type1).getPointedType() ;
		 * while(type1.getClass().equals(TypePointer.class)) { type1 =
		 * ((TypePointer)type1).getPointedType() ; if
		 * (type1.getClass().equals(TypePointer.class)) { type2 =
		 * ((TypePointer)type2).getPointedType() ; } }
		 * 
		 * if (type1 == type2) { return type1; } }
		 */
		// if (!type1.equals(type2))
		// ErrorStorage.add(new TypeError(super.row, super.column,
		// "cannot assign " + type2.getTypeName() + " to " + type1.getTypeName()));
		// else
		// return exp.typeCheck();
		return new TypeInt();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		return errors;
	}

}
