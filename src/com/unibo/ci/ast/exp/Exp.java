package com.unibo.ci.ast.exp;

import com.unibo.ci.ast.stmt.Statement;

/**
 * Classe che descrive una espressione generica
 */
public abstract class Exp extends Statement {
    public Exp(int row, int column) {
        super(row, column);
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: (Generic)\n";
    }

}
