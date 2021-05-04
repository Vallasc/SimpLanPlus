package com.unibo.ci.ast;

import java.util.ArrayList;
import com.unibo.ci.ast.types.Type;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.util.Environment;

public abstract class Node {
    protected final int row;
    protected final int column;

    public Node (int row , int column){
        this.row = row;
        this.column = column;
    }

    public abstract String toPrint(String indent);

    //fa il type checking e ritorna:
    //  per una espressione, il suo tipo (oggetto BoolTypeNode o IntTypeNode)
    //  per una dichiarazione, "null"
    public abstract Type typeCheck();

    public abstract String codeGeneration();

    public abstract ArrayList<SemanticError> checkSemantics(Environment env);
}
