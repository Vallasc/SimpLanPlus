package com.unibo.ci.ast.stmt;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
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
        return errors;
    }
    
}
