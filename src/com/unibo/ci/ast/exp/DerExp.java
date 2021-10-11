package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;

public class DerExp extends Exp {
    private final Exp child;

    public DerExp(int row, int column, Exp child) {
        super(row, column);
        this.child = child;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Der\n" + 
                child.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		/*stEntry = env.lookupSTentry(id);
		if (stEntry == null)
			errors.add(new SemanticError(row, column, "var " + id + " does not exist"));*/
		return errors;
    }

    @Override
    public Type typeCheck() { 
        /*if(stEntry == null)
            return null;
        return stEntry.getType();*/
        return null;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
