package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.STentry;

public class CallExp extends Node{

    private final String id;
    // Entry function definition
    private STentry entry; 
    private final ArrayList<Node> parlist; //TODO camibiare node in exp?

    public CallExp(int row, int column, String id, ArrayList<Node> parlist) {
        super(row, column);
        this.id = id;
        this.parlist = parlist;
    }

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type typeCheck() {
        // TODO 
        /// Controllo che entry.getType sia una funzione
        // Controllo numero e tipi dei parametri dato entry
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
        return null;
    }
    
}
