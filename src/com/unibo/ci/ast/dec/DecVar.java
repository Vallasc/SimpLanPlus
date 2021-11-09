package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.Environment.DuplicateEntryException;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

public class DecVar extends Dec {
    private final Exp exp;

    public DecVar(int row, int column, Type type, String id, Exp exp) {
        super(row, column, type, id);
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        
        return indent + "Declaration: Var\n" + 
                indent + "\tId: \"" + this.id + "\"\n" + 
                type.toPrint(indent + "\t") +
                (exp == null ? "" : exp.toPrint(indent + "\t"));
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        try {
            env.addDeclaration(id, type);

        } catch (DuplicateEntryException e) {
            // Aggiungere anche la riga e la colonna nel messaggio di errore
            errors.add(new SemanticError(row, column, "Already declared [" + id + "]"));
        }
        return errors;
    }

    @Override
    public Type typeCheck() {
		if (type instanceof TypeVoid)
			TypeErrorsStorage.add(new TypeError(row, column, "Variable type cannot be void"));

        if( exp == null)
            return new TypeVoid();


        Type expType = exp.typeCheck();
        if(expType == null)
            return null;

        if (!type.equals(expType)){
            TypeErrorsStorage.add( new TypeError(this.exp.getRow(), this.exp.getColumn(), 
                                "Expression type (" + expType + ") is not equal to variable type (" + type + ")"));
            return null;
        }
        return new TypeVoid();
    }
    
    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }


}