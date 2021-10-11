package com.unibo.ci.ast.stmt;

import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.dec.Arg;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.STentry;

public class CallStmt extends Exp {

    private final String id;
    // Entry function definition
    private STentry entry; 
    private final List<Exp> parlist;

    public CallStmt(int row, int column, String id, List<Exp> parlist) {
        super(row, column);
        this.id = id;
        this.parlist = parlist;
    }

    @Override
    public String toPrint(String indent) {
        StringBuilder sb = new StringBuilder(indent + "\t" + "Params\n");
        parlist.forEach(par -> {
            sb.append(par.toPrint(indent + "\t"));
        });
        return indent + "Call:\n" + 
                indent + "\tId: " + this.id + "\n" +
                sb.toString();
    }

    @Override
    public Type typeCheck() {
        /// Controllo che entry.getType sia una funzione
    	//qua ad esempio controllo che se dichiaro int pippo = 5, 
    	//non provi a fare pippo(x)
    	if (entry.getType().getClass() == TypeFunction.class) {
    		return entry.getType();
    	}
        
        return null;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
    	
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if( env.lookup(id) == null ){
            errors.add(new SemanticError(super.column, super.row, 
            "Function " + id + " not declared."));
        }
        
        // Controllo numero e tipi dei parametri dato entry
        TypeFunction stmTypeFun = ((TypeFunction)(entry.getType()));
        TypeFunction decTypeFun = ((TypeFunction)env.lookup(id)); 
    	if (!decTypeFun .getArguments().isEmpty()) {
    		//se la funzione ha dei parametri
    		if (stmTypeFun.getArguments().size() == decTypeFun.getArguments().size()) {
    			int arg2check = 0;
	    		for (Arg argDec : decTypeFun.getArguments()) {
	    			Arg argStm = stmTypeFun.getArguments().get(arg2check);
	    				
    				Type argStmType = argStm.typeCheck();
    				Type argDecType = argDec.typeCheck();

    				if (!argStmType.getClass().equals(argDecType.getClass())) {
    					 errors.add(new SemanticError(super.column, super.row, 
				            "Function " + id + ": " + argDecType.getTypeName() + " expected, got " + argStmType.getTypeName() + " instead."));
    				}
    					
	    			arg2check++;
	    		}
    		} else {
    			errors.add(new SemanticError(super.column, super.row, 
    		            "Function " + id + ": wrong number of parameters."));
    		}
    	} else {
    		//se la funzione non ha parametri
    		if (!stmTypeFun.getArguments().isEmpty()) {
    			errors.add(new SemanticError(super.column, super.row, "Function " + id + " has no parameters."));
    		}
    	}
    	
    	
        return errors.isEmpty() ? null : errors;

    }
    
}
