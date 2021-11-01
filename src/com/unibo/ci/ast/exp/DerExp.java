package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

public class DerExp extends LhsExp {
    private final LhsExp child;

    public DerExp(int row, int column, LhsExp child) {
        super(row, column);
        this.child = child;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Der\n" + 
                child.toPrint(indent + "\t");
    }

    
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
		return child.checkSemantics(env);
    }

    @Override
    public Type typeCheck() { 
        Type returnType = child.typeCheck();
        if(returnType == null){
            return null;
        }
        
        if( !(returnType instanceof TypePointer)){
            TypeErrorsStorage.add(new TypeError(row, column, "Dereferencing error"));
            return null;
        }
        return ((TypePointer) returnType).getPointedType();
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getVarId() {
        return child.getVarId();
    }

	@Override
	public ArrayList<EffectError> AnalyzeEffect(Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

}
