package com.unibo.ci.ast.dec;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.Environment.DuplicateSTEntryException;

public class DecVar extends Node {
    private final String id;
    private final Type type;
    private final Exp exp;

    public DecVar(int row, int column, String id, Type type, Exp exp) {
        super(row, column);
        this.id = id;
        this.type = type;
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type typeCheck() {
        // TODO Auto-generated method stub
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
            //Aggiungere anche la riga e la colonna nel messaggio di errore
            SemanticError error = new SemanticError(row, column, "Already declared [" + id + "]");
            semanticErrors.add(error);
            return semanticErrors;
        }

        return null;
    }
}