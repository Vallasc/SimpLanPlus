package com.unibo.ci.ast.exp;

import com.unibo.ci.ast.Node;

/**
 * Classe che descrive una espressione generica
 */
public abstract class Exp extends Node {
    public Exp(int row, int column) {
        super(row, column);
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: (Generic)\n";
    }

}
