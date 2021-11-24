package com.unibo.ci.ast.exp;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

public class DerExp extends LhsExp {
    private final LhsExp child;

    public DerExp(int row, int column, LhsExp child) {
        super(row, column);
        this.child = child;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Exp: Der\n" + child.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        return child.checkSemantics(env);
    }

    @Override
    public Type typeCheck() {
        Type returnType = child.typeCheck();
        if (returnType == null) {
            return null;
        }

        if (!(returnType instanceof TypePointer)) {
            TypeErrorsStorage.add(new TypeError(row, column, "Dereferencing error"));
            return null;
        }
        return ((TypePointer) returnType).getPointedType();
    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN DER " + this.toPrint("") + "\n" : "");    
        VarExp id = getVarId();  
        if(assignment){
            out += "mv $al $fp\n";
			for (int i = 0; i < (id.getNestingLevel() - id.getSTentry().getNestinglevel()); i++) {
				out += "lw $al 0($al)\n";
			}
			out += " addi $a0 $al " + ( id.getSTentry().getOffset() - 1) + "\n";
        } else {
            out = id.codeGeneration();
        }
    
        LhsExp pointer = child;
        while(pointer instanceof DerExp) { //dereference pointer
            out += " lw $a0 0($a0)\n";
            pointer = ((DerExp) pointer).child;
        }
        
        out += (debug ? ";END DER\n" : "");
        return out;
    }

    @Override
    public VarExp getVarId() {
        return child.getVarId();
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        return child.AnalyzeEffect(env);
    }

}
