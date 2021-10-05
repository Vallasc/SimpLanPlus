package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.Environment.DuplicateSTEntryException;

public class DecVar extends Dec { // TODO GIACOMO
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
    public Type typeCheck() {
        return null;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
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
}