package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;

public abstract class Dec extends Node {
    protected final Type type;
    protected final String id;

    public Dec(int row, int column, Type type, String id) {
        super(row, column);
        this.type = type;
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Declaration: \n" + type.toPrint(indent + "\t");
    }
    
    @Override
	public ArrayList<EffectError> AnalyzeEffect(Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

}
