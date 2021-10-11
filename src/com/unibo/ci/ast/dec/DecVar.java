package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.util.Environment.DuplicateSTEntryException;

public class DecVar extends Dec {
    private final Exp exp;

    public DecVar(int row, int column, Type type, String id, Exp exp) {
        super(row, column, type, id);
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Declaration: Var\n" + indent + "\tId: " + this.id + "\n" + type.toPrint(indent + "\t")
                + (exp != null ? exp.toPrint(indent + "\t") : "");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> semanticErrors = new ArrayList<SemanticError>();
        try {
            env.addDeclaration(id, type);
        } catch (DuplicateSTEntryException e) {
            // Aggiungere anche la riga e la colonna nel messaggio di errore
            SemanticError error = new SemanticError(row, column, "Already declared [" + id + "]");
            semanticErrors.add(error);
            return semanticErrors;
        }

        return null;
    }

    @Override
    public Type typeCheck() {
		if (type instanceof TypeVoid)
			TypeErrorsStorage.add(new TypeError(row, column, "Variable type cannot be void"));

		Type expType = exp.typeCheck();
		if (!type.equals(expType))
				TypeErrorsStorage.add(
						new TypeError(this.exp.getRow(), this.exp.getColumn(), 
                                "Expression type (" + expType + ") is not equal to variable type (" + type + ")"));
		return type;
    }
    
    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }
}