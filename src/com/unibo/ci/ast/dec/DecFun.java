package com.unibo.ci.ast.dec;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.EffectHelper.ETypes;
import com.unibo.ci.util.Environment.DuplicateEntryException;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.LabelManager;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.ast.stmt.block.BlockBase;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.errors.TypeError;

public class DecFun extends Dec {
    private final String id;
    private final Type type;
    private final List<Arg> args;
    private final BlockBase block;
    private final TypeFunction typeFun;

    // Code gen
    private String labelFun;
    private String labelSkip;
    private String labelEndFun;

    public DecFun(int row, int column, Type type, String id, List<Arg> args, BlockBase block) {
        super(row, column, type, id);
        this.id = id;
        this.type = type;
        this.args = args;
        this.block = block;
        this.typeFun = new TypeFunction(row, column, id, args.size(), type, args);

        this.labelFun = LabelManager.getInstance().newLabel(id);
        this.labelSkip = "ended" + labelFun;
        this.labelEndFun = "end" + labelFun;
        typeFun.setLabelStartFun(labelFun);
        typeFun.setLabelEndFun(labelEndFun);
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Declaration: Function\n" + indent + "\tId: \"" + this.id + "\"\n" + type.toPrint(indent + "\t")
                + printArgs(indent + "\t") + block.toPrint(indent + "\t");
    }

    private String printArgs(String indent) {
        StringBuilder sb = new StringBuilder(indent + "Args: \n");
        this.args.forEach((arg) -> {
            sb.append(arg.toPrint(indent + "\t"));
        });
        return sb.toString();
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(GammaEnv env) {

        ArrayList<SemanticError> semanticErrors = new ArrayList<SemanticError>();
        try {
            // Aggiungi tipo funzione
            env.addDeclaration(id, typeFun);
            // Nota: type dovrebbe essere T_1, ..., T_n -> T

        } catch (DuplicateEntryException e) {
            SemanticError error = new SemanticError(row, column, "Already declared [" + id + "]");
            semanticErrors.add(error);
            // return semanticErrors;
        }
        semanticErrors.addAll(block.checkSemanticsInjectArgs(env, args));
        return semanticErrors;
    }

    @Override
    public Type typeCheck() {
        for (Arg arg : args) {
            if (arg.typeCheck() == null)
                return null;
        }

        Type blockType = this.block.typeCheck();
        // System.out.println("DEBUG: il tipo del blocco nella funzione " + id + " è " +
        // blockType);
        // System.out.println("DEBUG: il tipo della funzione " + id + " è " +
        // this.type);
        if(typeFun.getReturnType() instanceof TypePointer){
            TypeErrorsStorage.add(new TypeError(super.row, super.column,
                    "Functions must not return pointer type"));
            return null;
        }
           
        if ((blockType == null && !(this.type instanceof TypeVoid))
                || (blockType != null && !this.type.equals(blockType))) {
            // Errore! Tipo del blocco e tipo di ritorno della funzione incompatibili
            TypeErrorsStorage.add(new TypeError(super.row, super.column,
                    "Function [" + this.id + "] must return with type [" + type.getTypeName() + "]"));
        }

        return typeFun;
        // TODO quanta memoria occupa la decfun? solo il numero deli argomenti?
        // (args.size())
    }

    @Override
    public String codeGeneration() {
        boolean debug = GlobalConfig.PRINT_COMMENTS;

        String out = (debug ? ";BEGIN DECFUN [" + id + "]\n" : "\n");
        out += "b " + labelSkip + "\n";
        out += labelFun + ":\n";
        out += "sw $ra -1($cl)\n";
        out += block.codeGeneration();
        out += "end" + labelFun + ":\n";
        out += "lw $ra -1($cl)\n";
        out += "lw $fp 1($cl)\n";
        out += "lw $sp 0($cl) \n";
        out += "addi $cl $fp 2\n";
        out += "jr $ra\n";
        out += (debug ? ";END DECFUN [" + id + "]\n" : "\n");
        out += labelSkip + ":\n";
        return out;
    }

    @Override
    public ArrayList<EffectError> AnalyzeEffect(SigmaEnv env) {
        ArrayList<EffectError> errors = new ArrayList<EffectError>();
        // SigmaEnv env_0 = new SigmaEnv(), env_1 = new SigmaEnv();
        // SigmaEnv env_0 = env.clone(), env_1 = env.clone();
        SigmaEnv env_f = env.clone();
        env_f.getTable().forEach( map -> map.entrySet().removeIf( entry -> entry.getValue().isNotFunction()));
        // SigmaEnv env_0 = env_f.clone(), env_1 = env_f.clone();
        SigmaEnv env_0 = env_f.clone(), env_1 = env_f.clone();
        env_0.newScope();
        env_1.newScope();

        args.forEach(arg -> {            
            env_0.addDeclaration(arg.getId(), ETypes.BOT);
            env_1.addDeclaration(arg.getId(), ETypes.BOT);
        });

        env_0.addDeclaration(id, env_0.clone(), env_1.clone());
        env_1.addDeclaration(id, env_0.clone(), env_1.clone());
        
        errors.addAll(AnalyzeEffect(env_0, env_1, env)); // env_0 e env_1 sono stati modificati
        

        // System.out.println("DEBUG: ho analizzato la funzione " + id + " che ha come
        // sigma_0 e sigma_1 rispettivamente:");
        // System.out.println(env_0.toPrint(""));
        // System.out.println(env_1.toPrint(""));

        // env_0.exitScope(); env_1.exitScope();

        return errors;

    }

    // calcolo col punto fisso degli effetti della funzione
    private ArrayList<EffectError> AnalyzeEffect(SigmaEnv env_0, SigmaEnv env_1, SigmaEnv env) {

        ArrayList<EffectError> errors = new ArrayList<EffectError>();


        // ci salviamo env_1 per la chiamata ricorsiva
        //env_0 = env_1.clone();
        // all'inizio env_1 e env_0 sono uguali, la valutazione degli s modifica env_1

        SigmaEnv e = env_0.clone();
    
        errors.addAll(block.AnalyzeEffectNoScope(e));
        
        if (equal_envs(env_1, e)){
            env.addDeclaration(id, env_0, env_1);
            return errors;
        }
        
        env_0.addDeclaration(id, env_0.clone(), e.clone());
        return AnalyzeEffect(env_0, e.clone(), env); // in realtà env_0 è env_1 prima dell'analisi degli effetti

    }

    private boolean equal_envs(SigmaEnv env_0, SigmaEnv env_1) {
        boolean is_equal = true;
        for (String id : env_0.getAllIDs().keySet()) {
            if (env_0.lookup(id) != null && !env_0.lookup(id).isNotFunction()){ //id è una funzione
                continue;
            }
            if (env_1.lookup(id) != null && !is_equal)
                break;
                
            is_equal &= env_1.lookup(id).getEtype() == env_0.lookup(id).getEtype();
        }

        return is_equal;
    }
}