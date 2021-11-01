package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.TypeErrorsStorage;

public class NewExp extends Exp {
    final Type type;

    public NewExp(int row, int column, Type type) {
        super(row, column);
        this.type = type;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: New\n" + type.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<SemanticError>();
    }

    @Override
    public Type typeCheck() {
        if (!(type instanceof TypeInt || type instanceof TypeBool || type instanceof TypePointer)) {
            TypeErrorsStorage.add(
                new TypeError(super.row, super.column, "expecting type [" + (new TypeBool()).getTypeName() + "] or [" + (new TypeInt()).getTypeName() + "], found [" + 
                    type.getTypeName() + "]"));
            return null;
        }
        return new TypePointer(type);
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public ArrayList<EffectError> AnalyzeEffect(Environment env) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
