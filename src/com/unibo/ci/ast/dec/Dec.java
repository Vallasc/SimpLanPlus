package com.unibo.ci.ast.dec;

import java.util.ArrayList;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.EEntry;
import com.unibo.ci.util.EffectHelper;

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
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        ArrayList<EffectError> errors = new ArrayList<EffectError>();
        EEntry entry = env.lookup(id);
        

        if (entry == null) { // entry non c'è
            env.addDeclaration(id, EffectHelper.ETypes.BOT);
        }
        else { // entry c'è già ma la variabile è stata cancellata
            if (entry.getEtype() == EffectHelper.ETypes.D) {
                entry.updateEffectType(EffectHelper.ETypes.BOT);
            } else { // la variabile non è stata cancellata
                entry.updateEffectType(EffectHelper.ETypes.T);
                errors.add(new EffectError(row, column, "Variable " + id + " declared variable"));
            }
        }

        return errors;
    }

}
