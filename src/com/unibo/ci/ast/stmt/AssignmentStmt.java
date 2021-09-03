package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.ErrorStorage;

public class AssignmentStmt extends Statement {

	private Exp exp1; 
	private Exp exp2;
	
	public AssignmentStmt(int row, int column, Exp exp1, Exp exp2) {
		super(row, column);
		// TODO Auto-generated constructor stub
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type typeCheck() {
		
		Type type1 = exp1.typeCheck();
		Type type2 = exp2.typeCheck();
		
		if (type1 == type2 ) {			
			return type1;
		} 
		
		//TODO continua da qua
		
		if (type1.getClass().equals(TypePointer.class)) {
			type1 = ((TypePointer)type1).getPointedType() ;
			while(type1.getClass().equals(TypePointer.class)) {
				type1 = ((TypePointer)type1).getPointedType() ;
				if (type1.getClass().equals(TypePointer.class)) {
					type2 = ((TypePointer)type2).getPointedType() ;
				}
			}
			
			if (type1 == type2) {
				return type1;
			}
		}
		
		ErrorStorage.add(new TypeError(super.row, super.column, "cannot assign " + type2.getTypeName() + " to " + type1.getTypeName()));
			
		return null;
	}
	
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
