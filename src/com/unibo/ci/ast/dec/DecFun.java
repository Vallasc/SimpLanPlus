package com.unibo.ci.ast.dec;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.Environment.DuplicateSTEntryException;
import com.unibo.ci.ast.stmt.block.BlockBase;
import com.unibo.ci.ast.types.TypeFunction;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.ast.errors.TypeError;

public class DecFun extends Dec {
    private final String id;
    private final Type type;
    private final List<Arg> args;
    private final BlockBase block;

    public DecFun(int row, int column, Type type, String id, List<Arg> args, BlockBase block) {
        super(row, column, type, id);
        this.id = id;
        this.type = type;
        this.args = args;
        this.block = block;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Declaration: Function\n" + 
                indent + "\tId: \"" + this.id + "\"\n" + 
                type.toPrint(indent + "\t") +
                printArgs(indent + "\t") + 
                block.toPrint(indent + "\t");
    }

    private String printArgs(String indent) {
        StringBuilder sb = new StringBuilder(indent + "Args: \n");
        this.args.forEach((arg) -> {
            sb.append(arg.toPrint(indent + "\t"));
        });
        return sb.toString();
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> semanticErrors = new ArrayList<SemanticError>();
        try {
            // Aggiungi tipo funzione
            env.addDeclaration(id, new TypeFunction(row, column, id, args.size(), type, args) );
            //TODO quanta memoria occupa la decfun? solo il numero deli argomenti? (args.size())

            // Nota: type dovrebbe essere T_1, ..., T_n -> T

        } catch (DuplicateSTEntryException e) {
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
        
        Type blockType = this.block.typeCheck() ;
        //System.out.println("DEBUG: il tipo del blocco nella funzione " + id + " è " + blockType);
        //System.out.println("DEBUG: il tipo della funzione " + id + " è " + this.type);
        if ( (blockType == null && !(this.type instanceof TypeVoid)) || (blockType != null && !this.type.equals( blockType))){
            //Errore! Tipo del blocco e tipo di ritorno della funzione incompatibili
            TypeErrorsStorage.add( new TypeError(super.row, super.column, 
                "Function [" + this.id + "] must return with type [" + type.getTypeName() +"]" ));
        }

        return new TypeFunction(row, column, id, args.size(), type, args); 
        //TODO quanta memoria occupa la decfun? solo il numero deli argomenti? (args.size())
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }


}