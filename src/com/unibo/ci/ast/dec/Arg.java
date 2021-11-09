package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.Environment.DuplicateEntryException;
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

    /*   
     * 
     *        ids(e)={x_1 ,..., x_n }
     * ------------------------------------- [Exp-e]
     *  ∑ ⊢ e : ∑ ⊳[x_1 ⟼ rw,..., x_n ⟼ rw]
     *  
     * */
    
    @Override
	public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {

    	ArrayList<EffectError> errors = null;
    	
    	//TODO possiamo ridichiarare una variabile cancellata?
    	//TODO come gestiamo 'int x;' e 'int x = 5;'? (dichiarazione vs dichiarazione e assegnamento)
    	// (perché nell'altro progetto hanno dichiarazione e espressione, che può essere null nel primo caso e != da null nel secondo)
    	
		try {
			env.addDeclaration(id, EffectHelper.ETypes.BOT); //TODO abbiamo la duplicate exception per gli effetti?
		} catch(Exception e) {
			e.printStackTrace();
			errors = new ArrayList<EffectError>();
			errors.add(new EffectError(column, column, "TODO"));
		}
		
		return errors;
		
		
	
	}


}
