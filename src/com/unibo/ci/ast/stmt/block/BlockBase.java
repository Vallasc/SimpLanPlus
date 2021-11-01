package com.unibo.ci.ast.stmt.block;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.stmt.CallStmt;
import com.unibo.ci.ast.stmt.Statement;
import com.unibo.ci.ast.dec.Dec;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

/**
 * Example: { }
 */
public class BlockBase extends Block {
	final List<Dec> declarations;
	final List<Statement> statements;

	public BlockBase(List<Dec> declarations, List<Statement> statements, int row, int column) {
		super(row, column);
		this.declarations = declarations;
		this.statements = statements;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		env.newScope();
		
		declarations.forEach(dec -> {
			errors.addAll(dec.checkSemantics(env));
		});
		statements.forEach(stmt -> {
			errors.addAll(stmt.checkSemantics(env));
		});
		//System.out.println(env.toPrint(""));
		env.exitScope();

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

		ArrayList<Type> stmtReturn = new ArrayList<Type>();
		statements.forEach(stmt -> {
			Type type = stmt.typeCheck();
			if(!(stmt instanceof CallStmt)){ 
				/*DUBBIO DI SHIMO: perché controlliamo tutti gli statement tranne le call? Non ci interessano solo gli statement 'return'?*/
				stmtReturn.add(type);
			}
		});

		Type returnType = stmtReturn.stream()
				.reduce(new TypeVoid(), (accumulator, element) -> {
					if (accumulator == null) {
						return null;
					}
					if(element instanceof TypeVoid){
						return accumulator;
					}
					if((element instanceof TypeInt || element instanceof TypeBool) 
						&& (accumulator instanceof TypeVoid || accumulator.equals(element))){
						/*DUBBIO DI SHIMO: se abbiamo un blocco con tante 'return' semplici (quindi solo valori 'void') 
						 * l'accumulatore dovrebbe essere void, ma se all'ultimo becchiamo una 'return 3' la reduce() non restituisce 'int'?
						 * E questo non è un errore? (perchè un blocco restituisce sia void che int) */
						return element;
					}
					return null;
				});

		if(returnType == null){
			TypeErrorsStorage.add(new TypeError(row, column, "return type mismatch in block element"));
		}
		return returnType;
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
