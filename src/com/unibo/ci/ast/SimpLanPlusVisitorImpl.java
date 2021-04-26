package com.unibo.ci.ast;

import com.unibo.ci.parser.*;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    @Override 
    public Node visitBlock(SimpLanPlusParser.BlockContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitStatement(SimpLanPlusParser.StatementContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitDecFun(SimpLanPlusParser.DecFunContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitDecVar(SimpLanPlusParser.DecVarContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitType(SimpLanPlusParser.TypeContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitArg(SimpLanPlusParser.ArgContext ctx) { return visitChildren(ctx); }

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
    public Node visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitBinExp(SimpLanPlusParser.BinExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitDerExp(SimpLanPlusParser.DerExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitNewExp(SimpLanPlusParser.NewExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitValExp(SimpLanPlusParser.ValExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitNegExp(SimpLanPlusParser.NegExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitCallExp(SimpLanPlusParser.CallExpContext ctx) { return visitChildren(ctx); }

    @Override 
    public Node visitNotExp(SimpLanPlusParser.NotExpContext ctx) { return visitChildren(ctx); }
}