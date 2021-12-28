package com.unibo.ci.ast.stmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.errors.TypeError;
import com.unibo.ci.ast.exp.DerExp;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.exp.LhsExp;
import com.unibo.ci.ast.exp.VarExp;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.EffectHelper;
import com.unibo.ci.util.EffectHelper.ETypes;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.STentry;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.util.SigmaEnv;

public class CallStmt extends Exp {

    private final String id;
    // Entry function definition
    private STentry entry;
    private final List<Exp> parlist;
    private int nestingLevel;

    public CallStmt(int row, int column, String id, List<Exp> parlist) {
        super(row, column);
        this.id = id;
        this.parlist = parlist;
    }

    @Override
    public String toPrint(String indent) {
        StringBuilder sb = new StringBuilder(indent + "\t" + "Params:\n");
        parlist.forEach(par -> {
            sb.append(par.toPrint(indent + "\t\t"));
        });
        return indent + "Call:\n" +
                indent + "\tId: " + this.id + "\n" +
                sb.toString();
    }

    @Override
    public Type typeCheck() {
        if (entry == null)
            return null;

        /// Controllo che entry.getType sia una funzione
        if (!(entry.getType() instanceof TypeFunction)) {
            TypeErrorsStorage.add(new TypeError(row, column,
                    "[" + entry.getId() + "] is not a function"));
        }

        TypeFunction typeFun = ((TypeFunction) (entry.getType()));
        if (parlist.size() != typeFun.getArguments().size())
            return null;

        for (int i = 0; i < parlist.size(); i++) {
            Type funParType = typeFun.getArguments().get(i).typeCheck();
            if (funParType == null) {
                TypeErrorsStorage.add(new TypeError(row, column,
                        "[" + entry.getId() + "] is not a function"));
                return null;
            }

            Type callParType = parlist.get(i).typeCheck();

            if (!funParType.equals(callParType)) {
                TypeErrorsStorage.add(new TypeError(row, column,
                        "Argument " + i + " must be of type [" + funParType.getTypeName() + "]"));
                return null;
            }
        }

        return typeFun.getReturnType();
    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN CALL FUN [" + id + "]\n" : "");
        out += "push $fp\n";
        out += "push $sp\n";
        out += "mv $cl $sp\n";

        out += "addi $t1 $cl 2\n";
        out += "sw $t1 0($cl)\n";

        out += "addi $sp $sp -1\n";

        TypeFunction typeFun = ((TypeFunction) (entry.getType()));

        if (typeFun.getLabelEndFun() == null || !(typeFun.getLabelEndFun().contains(id)))
            out += "mv $al $fp\n";
        else
            out += "lw $al 0($fp)\n";

        for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++)
            out += "lw $al 0($al)\n";

        out += "push $al\n";
        for (Exp p : parlist) {
            out += p.codeGeneration() + "push $a0" + (debug ? " ;pushing " + "\n" : "\n");
        }
        out += "mv $fp $sp\n";
        out += "addi $fp $fp " + parlist.size() + "\n";
        out += "jal " + id; // decfun saves ra firstly

        out += (debug ? ";END DELETE\n" : "");
        return out;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {

        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        entry = env.lookup(id);
        nestingLevel = env.getNestingLevel();

        if (entry == null) {
            errors.add(new SemanticError(super.row, super.column,
                    "Function [" + id + "] not declared"));
            return errors;
        }

        if (!(entry.getType() instanceof TypeFunction)) {
            errors.add(new SemanticError(super.row, super.column,
                    "Function [" + id + "] not declared"));
            return errors;
        }

        TypeFunction typeFun = ((TypeFunction) (entry.getType()));

        parlist.forEach((par) -> errors.addAll(par.checkSemantics(env)));

        if (parlist.size() != typeFun.getArguments().size()) {
            errors.add(new SemanticError(super.row, super.column,
                    "Bad number of parameters"));
            return errors;
        }

        return errors;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        ArrayList<EffectError> errors = new ArrayList<EffectError>();

        // SigmaEnv sigma_0 = env.lookup(id).getSigma0();
        SigmaEnv sigma_1 = env.lookup(id).getSigma1();

        // sigma secondo associa ad un nome di variabile (parametro attuale della
        // funzione) un effetto, servirà per fare il par
        // logica: se ho una funzione pippo(var a, var b, var c) e la chiamo come
        // pippo(x,x,y) dovrò fare il par sugli effetti associati ad x (ne avrò due,
        // perché x viene legata ai parametri formali 'a' e 'b'
        HashMap<String, ArrayList<ETypes>> sigma_secondo = new HashMap<String, ArrayList<ETypes>>();
        int position = 0; // conta la posizione della variabile - serve per corrispondenza parametri
                          // attuali e formali
        for (Exp par : parlist) {
            if (par.typeCheck() instanceof TypePointer) {
            	
                String formal_parameter = ((TypeFunction) entry.getType()).getArguments().get(position).getId();
                // System.out.println("DEBUG: cerco \n " + formal_parameter + " \n nell'ambiente
                // " + sigma_1.toPrint(""));
                ETypes tmp = EffectHelper.seq(
                        env.lookup(((LhsExp) par).getVarId().getId() /* parametri attuali */).getEtype(),
                        sigma_1.lookup(
                                formal_parameter/* partametri formali */)
                                .getEtype());

                String var_id = ((LhsExp) par).getVarId().getId();
                ArrayList<ETypes> valEffectList = sigma_secondo.getOrDefault(var_id, new ArrayList<ETypes>());
                valEffectList.add(tmp);
                sigma_secondo.put(((LhsExp) par).getVarId().getId(), valEffectList);

            }
            position++;
        }

        sigma_secondo.forEach((id, effect_list) -> {
            // calcoliamo effettivamente par
            ETypes tmp = effect_list.size() == 1 ? effect_list.get(0)
                    : effect_list
                            .stream()
                            .reduce((a, b) -> {
                                return EffectHelper.par(a, b);
                            }).get();

            // controlliamo gli errori
            if (tmp != null && tmp == ETypes.T) {
                errors.add(new EffectError(row, column,
                        "Aliasing error: pointer " + "[" + id + "]" + " could be deleted twice."));
            }

            // update
            env.lookup(id).updateEffectType(tmp);

        });

        return errors;
    }

}
