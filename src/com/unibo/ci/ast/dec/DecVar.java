package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.Environment.DuplicateEntryException;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.EEntry;
import com.unibo.ci.util.EffectHelper.ETypes;
import com.unibo.ci.ast.errors.EffectError;

public class DecVar extends Dec {
    private final Exp exp;

    public DecVar(int row, int column, Type type, String id, Exp exp) {
        super(row, column, type, id);
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {

        return indent + "Declaration:\n" + indent + "\tId: \"" + this.id + "\"\n" + type.toPrint(indent + "\t")
                + (exp == null ? "" : exp.toPrint(indent + "\t"));
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if (exp != null)
            errors.addAll(exp.checkSemantics(env));
        try {
            env.addDeclaration(id, type);

        } catch (DuplicateEntryException e) {
            // Aggiungere anche la riga e la colonna nel messaggio di errore
            errors.add(new SemanticError(row, column, "Already declared [" + id + "]"));
        }
        return errors;
    }

    @Override
    public Type typeCheck() {
        if (type instanceof TypeVoid)
            TypeErrorsStorage.add(new TypeError(row, column, "Variable type cannot be " + type.getTypeName()));

        if (exp == null)
            return new TypeVoid();

        Type expType = exp.typeCheck();
        if (expType == null)
            return null;

        if (!type.equals(expType)) {
            TypeErrorsStorage.add(new TypeError(this.exp.getRow(), this.exp.getColumn(), "Expression type ["
                    + expType.getTypeName() + "] is not equal to declared type [" + type.getTypeName() + "]"));
            return null;
        }
        return new TypeVoid();
    }

    /*
     * public Type typeCheck2() { Type typeLeft = left.typeCheck(); Type typeExp =
     * exp.typeCheck(); if(typeExp == null) return null;
     * 
     * if(!typeLeft.equals(typeExp)){ TypeErrorsStorage.add(new TypeError(super.row,
     * super.column, "cannot assign [" + typeExp.getTypeName() + "] to [" +
     * typeLeft.getTypeName() + "]")); return null; } return new TypeVoid(); }
     */

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN DECVAR" + this.toPrint("") + "\n" : "");        
		out += "addi $sp $sp 1" + (debug ? " ;allocates space on the stack for arg [" + id + "]\n" : "\n");
        
        if(exp == null)
            out += "addi $sp $sp -1\n";
        else
            out += exp.codeGeneration() + "\n" + "push $a0\n";

        out += (debug ? ";END DECVAR\n" : "");
        return out;
    }

    /*
     * 
     * ids(e)={x_1 ,..., x_n } ------------------------------------------ [Exp-e] ∑
     * ⊢ e : ∑ ⊳ [x_1 ⟼ rw,..., x_n ⟼ rw]
     * 
     */

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {

        ArrayList<EffectError> errors = new ArrayList<EffectError>();
        EEntry entry = env.lookup(id);

        /*
         * ^^int xy; xy = ^int x; delete x; xy = x;
         * 
         * ^int x; [ x -> BOTTOM]
         * 
         * 
         * 
         * ^int x = 5; ^int x;
         */

        if (entry == null) { // entry non c'è
        	env.addDeclaration(id, EffectHelper.ETypes.BOT);
            
        }

        /*
         * else { env.lookup(id).updateEffectType(EffectHelper.ETypes.T); errors.add(new
         * EffectError(row, column, "Variable " + id + " already declared")); }
         */

        if (exp != null) {
            errors.addAll(exp.AnalyzeEffect(env));
            env.lookup(id).updateEffectType(EffectHelper.seq(env.lookup(id).getEtype(), ETypes.RW));
        }

        /*
         * 
         * -------------------------[Var-e] ∑ ⊢ T x; : ∑[x⟼ ⊥]
         * 
         * 
         * 
         * 
         * Γ ⊢ e : T' x ∉ dom(top(Γ)) T=T' --------------------------------------[VarD]
         * Γ ⊢ T x = e ; : Γ[x ⟼ T]
         */
        
        
        
        return errors;

    }

}