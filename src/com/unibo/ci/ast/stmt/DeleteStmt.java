package com.unibo.ci.ast.stmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.STentry;

public class DeleteStmt extends Statement {

    private String id;

    public DeleteStmt(int row, int column, String id) {
        super(row, column);
        this.id = id;
    }

    public STentry existElement(Environment env, String id) {

        Iterator<HashMap<String, STentry>> it = env.getSymTable().descendingIterator();

        while (it.hasNext()) {
            if (it.next().containsKey(id) && !(it.next().get(id).isDeleted())) {

                return it.next().get(id);
            }
        }
        return null;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Stmt: Remove \"" + id + "\"\n";
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
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        /*STentry candidate = existElement(env, id);

        if (candidate != null && !(candidate.isDeleted())) {
            candidate.setDeleted(true);
        }
        if ((candidate == null)) {
            errors.add(new SemanticError(super.column, super.row,
                    "Cannot delete variable " + id + ", cause it is not declared."));
            return errors;
        }

        if (candidate.isDeleted()) {
            errors.add(new SemanticError(super.column, super.row, "Variable " + id + " already deleted."));
        }*/

        STentry entry = env.lookupSTentry(id);
        if (entry == null) {
            errors.add(new SemanticError(super.column, super.row,
                    "Cannot delete variable " + id + ", cause it is not declared."));
        }

        return errors;
    }
}
