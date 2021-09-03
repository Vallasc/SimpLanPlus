package com.unibo.ci.ast;

import java.util.ArrayList;

import com.unibo.ci.ast.dec.Arg;
import com.unibo.ci.ast.dec.DecFun;
import com.unibo.ci.ast.dec.DecVar;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.stmt.BlockBaseStmt;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.parser.*;
import com.unibo.ci.parser.SimpLanPlusParser.ArgContext;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    @Override 
    public Node visitBlock(SimpLanPlusParser.BlockContext ctx) { 
    	BlockBaseStmt bbn = new BlockBaseStmt(ctx.start.getLine(), ctx.start.getCharPositionInLine()) ;
    	return  bbn;
    	
    }

    @Override 
    public Node visitStatement(SimpLanPlusParser.StatementContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx) { return visitChildren(ctx); }

    @Override 
    public DecFun visitDecFun(SimpLanPlusParser.DecFunContext ctx) {
        ArrayList<Arg> args = new ArrayList<>();
        for (ArgContext argC : ctx.arg())
            args.add( (Arg) visitChildren(argC));
        Type type = ctx.type() == null ? new TypeVoid() : (Type) visitChildren(ctx.type());
        return new DecFun(ctx.start.getLine(), ctx.start.getCharPositionInLine(), 
                            type, 
                            args,
                            (Block) visitChildren(ctx.block()));
    }

    @Override 
    public DecVar visitDecVar(SimpLanPlusParser.DecVarContext ctx) {
        DecVar test = new DecVar(ctx.start.getLine(), ctx.start.getCharPositionInLine(), 
                            ctx.ID().getText(),
                            (Type) visitChildren(ctx.type()),
                            (Exp) visitChildren(ctx.exp()));
        return test;
    }

    @Override 
    public Type visitType(SimpLanPlusParser.TypeContext ctx) { 
        if( ctx.type() != null )
            return new TypePointer(ctx.start.getLine(), ctx.start.getCharPositionInLine(), 
                                    (Type) visitChildren(ctx.type()));
        if( ctx.getText().equals("int"))
            return new TypeInt(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        if( ctx.getText().equals("bool"))
            return new TypeBool(ctx.start.getLine(), ctx.start.getCharPositionInLine());

        // Not reachable
        return null;
    }

    @Override 
    public Arg visitArg(SimpLanPlusParser.ArgContext ctx) {
        return (Arg)visitChildren(ctx);
    }

    @Override 
    public Node visitAssignment(SimpLanPlusParser.AssignmentContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitLhs(SimpLanPlusParser.LhsContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitDeletion(SimpLanPlusParser.DeletionContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitPrint(SimpLanPlusParser.PrintContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitRet(SimpLanPlusParser.RetContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitIte(SimpLanPlusParser.IteContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitCall(SimpLanPlusParser.CallContext ctx) { return visitChildren(ctx); }

    @Override 
    public Exp visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitBinExp(SimpLanPlusParser.BinExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitDerExp(SimpLanPlusParser.DerExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitNewExp(SimpLanPlusParser.NewExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitValExp(SimpLanPlusParser.ValExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitNegExp(SimpLanPlusParser.NegExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitCallExp(SimpLanPlusParser.CallExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitNotExp(SimpLanPlusParser.NotExpContext ctx) { return (Exp) visitChildren(ctx); }
}