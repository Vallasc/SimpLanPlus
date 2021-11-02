package com.unibo.ci.ast.stmt.block;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.unibo.ci.ast.stmt.ReturnStmt;
import com.unibo.ci.ast.stmt.Statement;
import com.unibo.ci.ast.dec.Dec;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;

/**
 * Example: { }
 */
public class BlockFun extends Block {
	final List<Dec> declarations;
	final List<Statement> statements;

	public BlockFun(List<Dec> declarations, List<Statement> statements, int row, int column) {
		super(row, column);
		this.declarations = declarations;
		this.statements = statements;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		//Non creo un nuovo scope perché viene fatto in 
		//DecFun
		declarations.forEach(dec -> {
			errors.addAll(dec.checkSemantics(env));
		});
		statements.forEach(stmt -> {
			errors.addAll(stmt.checkSemantics(env));
		});
		System.out.println(env.toPrint(""));
		
		boolean checkReturnStmt = ((TypeFunction)env.lookupFunction().getType()).getReturnType().getClass().isInstance(TypeVoid.class);
		//di base checkReturnStmt dovrebbe essere false e diventare true se troviamo uno statement "return", però se la funzione è void allora checkReturnStmt è true
		ArrayList<Statement> myStatements = new ArrayList<Statement>(); 
		myStatements.addAll(statements);
		for(Statement stmt : myStatements) {
			
			/*if (stmt.getClass().isInstance(Block.class)) { 
			 * //TODO servirebbe per cercare ricorsivamente negli statement, però
			 * //stmt non può essere convertito a Block.
			 * //Non è necessario cercare ricorsivamente se this.statements contiene già tutti gli statements dei sottoblocchi, ma non lo so
				myStatements.addAll(((BlockBase)stmt).statements);
			}*/
			
			if (stmt.getClass().equals(ReturnStmt.class)) {
				checkReturnStmt = true;
				break;	
				
			}
		}
		
		//Nota: dobbiamo solo controllare che ci sia almeno un return. Questo non significa che ogni
		//sottobranch della funzione abbia uno statement di return:
		//es. void pippo() { if (2==1) {return;}  }
		//ma questa parte verrà fatta nell'analisi degli effetti.
		//In ReturnStmt viene anche fatta la typecheck del return (grazie alla funzione lookupFunction()).
		
		if (checkReturnStmt == false) {
			errors.add(new SemanticError(row, column, "Function " + env.lookupFunction().getId() + " has no return statement"));
		}
		
		return errors;
	}

	@Override
	public String toPrint(String indent) {
		StringBuilder out = new StringBuilder(indent + "Block\n");
		declarations.forEach(dec -> {
			out.append(dec.toPrint(indent + "\t"));
		});
		statements.forEach(stmt -> {
			out.append(stmt.toPrint(indent + "\t"));
		});
		return out.toString();
	}

	@Override
	public Type typeCheck() {
		declarations.forEach(dec -> {
			dec.typeCheck();
		});
		statements.forEach(stmt -> {
			stmt.typeCheck();
		});
		return new TypeVoid();
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

}
