package com.unibo.ci.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.unibo.ci.ast.dec.Arg;
import com.unibo.ci.ast.dec.Dec;
import com.unibo.ci.ast.dec.DecFun;
import com.unibo.ci.ast.dec.DecVar;
import com.unibo.ci.ast.exp.Exp;
import com.unibo.ci.ast.exp.ValExp;
import com.unibo.ci.ast.stmt.block.BlockBase;
import com.unibo.ci.ast.stmt.Statement;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeBool;
import com.unibo.ci.ast.types.TypeInt;
import com.unibo.ci.ast.types.TypePointer;
import com.unibo.ci.ast.types.TypeVoid;
import com.unibo.ci.parser.*;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    @Override 
    public BlockBase visitBlock(SimpLanPlusParser.BlockContext ctx) { 
        List<Statement> statements = ctx.statement().stream().map(this::visitStatement).collect(Collectors.toList());
        List<Dec> declarations = ctx.declaration().stream().map(this::visitDeclaration).collect(Collectors.toList());

        return new BlockBase(declarations, statements, ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override 
    public Statement visitStatement(SimpLanPlusParser.StatementContext ctx) { 
        return (Statement) visit(ctx.getChild(0));
    }

    @Override 
    public Dec visitDeclaration(SimpLanPlusParser.DeclarationContext ctx) {
        return (Dec) visit(ctx.getChild(0));
    }

    @Override 
    public Dec visitDecFun(SimpLanPlusParser.DecFunContext ctx) {
        ArrayList<Arg> args = new ArrayList<>();
        ctx.arg().forEach(arg -> args.add(visitArg(arg)));
        Type type = ctx.type() == null ? new TypeVoid() : (Type) visit(ctx.type());
        return new DecFun(ctx.start.getLine(), ctx.start.getCharPositionInLine(), type, ctx.ID().getText(), args, (BlockBase) visit(ctx.block()));
    }

    @Override 
    public Arg visitArg(SimpLanPlusParser.ArgContext ctx) {
        return (Arg)visitChildren(ctx);
    }

    @Override 
    public Dec visitDecVar(SimpLanPlusParser.DecVarContext ctx) {
        return new DecVar(ctx.start.getLine(), ctx.start.getCharPositionInLine(), 
                            (Type) visit(ctx.type()),
                            ctx.ID().getText(),
                            ctx.exp() != null ? (Exp) visit(ctx.exp()) : null);
    }

    @Override 
    public Type visitType(SimpLanPlusParser.TypeContext ctx) {
        if( ctx.getText().equals("int"))
            return new TypeInt(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        if( ctx.getText().equals("bool"))
            return new TypeBool(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        return new TypePointer(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Type) visit(ctx.type()));
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
    public Exp visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) { 
        return (Exp) visit(ctx.exp()); 
    }

    @Override 
    public Exp visitBinExp(SimpLanPlusParser.BinExpContext ctx) { 
        return (Exp) visitChildren(ctx); 
    }

    @Override 
    public Exp visitDerExp(SimpLanPlusParser.DerExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitNewExp(SimpLanPlusParser.NewExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitValExp(SimpLanPlusParser.ValExpContext ctx) { 
        return new ValExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), Integer.parseInt(ctx.NUMBER().getText())); 
    }

    @Override 
    public Exp visitNegExp(SimpLanPlusParser.NegExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitCallExp(SimpLanPlusParser.CallExpContext ctx) { return (Exp) visitChildren(ctx); }

    @Override 
    public Exp visitNotExp(SimpLanPlusParser.NotExpContext ctx) { return (Exp) visitChildren(ctx); }
}