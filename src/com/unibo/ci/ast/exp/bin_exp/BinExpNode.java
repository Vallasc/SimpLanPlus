package com.unibo.ci.ast.exp.bin_exp;

import java.util.ArrayList;

import com.unibo.ci.util.Environment;
import com.unibo.ci.ast.Node;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.types.Type;

public class BinExpNode extends Exp {

    protected final Exp right;
    protected final Exp left;

    public BinExpNode(int row, int column, Exp right, Exp left) {
        super(row, column);
        this.right = right;
        this.left = left;
    }

    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type typeCheck() {

        /*toRet.addAll(leftSide.checkSemantics(e));
		toRet.addAll(rightSide.checkSemantics(e));

        toRet.addAll(leftSide.inferBehaviour(e));
		toRet.addAll(rightSide.inferBehaviour(e));

        if (! ( SimpLanlib.isSubtype(left.typeCheck(),new IntTypeNode()) &&
	            SimpLanlib.isSubtype(right.typeCheck(),new IntTypeNode()) ) ) {
	      System.out.println("Non integers in multiplication");
	      System.exit(0);
	    }
	    
        return new IntTypeNode();
        // TODO Auto-generated method stub*/
        
        return null;
    }

    @Override
    public String codeGeneration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
