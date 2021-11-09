package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;

public class Arg extends Node {
    private final String id;
    private final Type type;

    public Arg(int row, int column, String id, Type type) {
        super(row, column);
        this.id = id;
        this.type = type;
    }

    @Override
    public String toPrint(String indent) {
        String args = indent + "Arg: \n" + 
                        indent + "\t Id: " + id + "\n" + 
                        type.toPrint(indent + "\t");
        return args;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        try {
            env.addDeclaration(id, type);
        } catch (DuplicateEntryException e) {
            errors.add(new SemanticError(row, column, "Already declared [" + id + "]"));
        }
        return errors;
    }
    
    @Override
    public Type typeCheck() {
        return type;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
	public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
		// TODO Auto-generated method stub
		return null;
	}


}
