package com.unibo.ci.ast.stmt;

import java.util.ArrayList;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

public class DeleteStmt extends Statement {

    private String id;
    private STentry stEntry;
    private int nestingLevel;

    public DeleteStmt(int row, int column, String id) {
        super(row, column);
        this.id = id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        stEntry = env.lookup(id);
        nestingLevel = env.getNestingLevel();
        if (stEntry == null) {
            errors.add(new SemanticError(super.column, super.row,
                    "cannot delete variable " + id + ", cause it is not declared."));
        }
        return errors;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Stmt: delete \"" + id + "\"\n";
    }

    @Override
    public Type typeCheck() {

        if (stEntry == null) {
            return null;
        }

        if (!(stEntry.getType() instanceof TypePointer)) {
            TypeErrorsStorage.add(new TypeError(super.column, super.row, "Variable type must be [POINTER[*]]"));
        }
        return stEntry.getType();

    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN DELETE " + this.toPrint("") + "\n" : "");        
        out += "mv $al $fp \n";

        for (int i = 0; i < nestingLevel - stEntry.getNestinglevel(); i++) {
            out += "lw $al 0($al)\n";
        }

        int offset = stEntry.getOffset() - 1;
        out += "\t lw $a0 " + offset + "($al)\n";
        
        out += "del $a0\n";
        out += (debug ? ";END DELETE\n" : "");
        return out;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        // TODO Auto-generated method stub
        return new ArrayList<EffectError>();
    }

}
