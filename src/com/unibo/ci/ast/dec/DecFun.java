package com.unibo.ci.ast.dec;

import java.util.ArrayList;
import java.util.List;

import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.stmt.block.Block;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.Environment.DuplicateSTEntryException;
import com.unibo.ci.ast.stmt.block.BlockBase;
import com.unibo.ci.ast.types.TypeFunction;

public class DecFun extends Dec {
    private final String id;
    private final List<Arg> args;
    private final BlockBase block;

    public DecFun(int row, int column, Type type, String id, List<Arg> args, BlockBase block) {
        super(row, column, type, id);
        this.id = id;
        this.args = args;
        this.block = block;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "Declaration: Function\n" + indent + "\tId: " + this.id + "\n" + type.toPrint(indent + "\t")
                + (id != null && block != null ? id + " " + printArgs(indent + "\t") : "");
    }

    private String printArgs(String indent) {
        String args = "Args: ";
        this.args.forEach((arg) -> {
            args.concat(arg.toString());
            args.concat(" ");
        });
        return args;
    }

    @Override
    public Type typeCheck() {

        for (Arg arg : args) {
            if (arg.typeCheck() == null)
                return null;
        }

        return block.typeCheck() == null ? null : new TypeFunction(row, column, id, 0, type, args);

    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        ArrayList<SemanticError> semanticErrors = new ArrayList<SemanticError>();
        try {
            env.addDeclaration(id, type);

            // Nota: type dovrebbe essere T_1, ..., T_n -> T

        } catch (DuplicateSTEntryException e) {
            SemanticError error = new SemanticError(row, column, "Already declared [" + id + "]");
            semanticErrors.add(error);
            // return semanticErrors;
        }

        env.newScope();

        for (Arg arg : args) {
            try {
                env.addDeclaration(arg.getId(), arg.typeCheck());
            } catch (DuplicateSTEntryException e) {
                semanticErrors.add(new SemanticError(row, column,
                        "Function " + id + ": repeated paramateres in function definition"));
            }
        }

        semanticErrors.addAll(block.checkSemantics(env));
        env.exitScope();
        return null;
    }
}