package com.unibo.ci.ast;

import java.util.List;
import java.util.stream.Collectors;

import com.unibo.ci.ast.dec.*;
import com.unibo.ci.ast.exp.*;
import com.unibo.ci.ast.exp.bin_exp.*;
import com.unibo.ci.ast.stmt.*;
import com.unibo.ci.ast.stmt.block.*;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.parser.*;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {

    private int blockNL = 0;

    @Override
    public BlockBase visitBlock(SimpLanPlusParser.BlockContext ctx) {
        List<Statement> statements = ctx.statement().stream().map(this::visitStatement).collect(Collectors.toList());
        List<Dec> declarations = ctx.declaration().stream().map(this::visitDeclaration).collect(Collectors.toList());
        BlockBase block = new BlockBase(declarations, statements, ctx.start.getLine(), ctx.start.getCharPositionInLine());
        block.setMain(blockNL++ == 0);
        return block;
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
        List<Arg> args = ctx.arg().stream().map(this::visitArg).collect(Collectors.toList());
        Type type = ctx.type() == null ? new TypeVoid() : (Type) visit(ctx.type());
        return new DecFun(ctx.start.getLine(), ctx.start.getCharPositionInLine(), type, ctx.ID().getText(), args,
                (BlockBase) visit(ctx.block()));
    }

    @Override
    public Arg visitArg(SimpLanPlusParser.ArgContext ctx) {
        return new Arg(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.ID().getText(), (Type) visit(ctx.type()) );
    }

    @Override
    public Dec visitDecVar(SimpLanPlusParser.DecVarContext ctx) {
        return new DecVar(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Type) visit(ctx.type()),
                ctx.ID().getText(), ctx.exp() != null ? (Exp) visit(ctx.exp()) : null);
    }

    @Override
    public Type visitType(SimpLanPlusParser.TypeContext ctx) {
        if (ctx.getText().equals("int"))
            return new TypeInt(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        if (ctx.getText().equals("bool"))
            return new TypeBool(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        return new TypePointer(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Type) visit(ctx.type()));
    }

    @Override
    public Statement visitAssignment(SimpLanPlusParser.AssignmentContext ctx) {
        Exp exp = (Exp) visit(ctx.exp());
        LhsExp left = (LhsExp) visit(ctx.lhs());
        left.setAssignment(true);
        return new AssignmentStmt(ctx.start.getLine(), ctx.start.getCharPositionInLine(), left, exp);
    }

    @Override
    public LhsExp visitLhs(SimpLanPlusParser.LhsContext ctx) {
        return ctx.lhs() != null ?
            new DerExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (LhsExp) visit(ctx.lhs())) : 
            new VarExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.ID().getText());
    }

    @Override
    public Statement visitDeletion(SimpLanPlusParser.DeletionContext ctx) {
        return new DeleteStmt(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.ID().getText());
    }

    @Override
    public Node visitPrint(SimpLanPlusParser.PrintContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Statement visitRet(SimpLanPlusParser.RetContext ctx) {
        if(ctx.exp() != null)
            return new ReturnStmt(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Exp) visit(ctx.exp()));
        else 
            return new ReturnStmt(ctx.start.getLine(), ctx.start.getCharPositionInLine(), null);

    }

    @Override
    public Node visitIte(SimpLanPlusParser.IteContext ctx) {
        Exp exp = (Exp) visit(ctx.exp());
        return new IteStmt(exp, visitStatement(ctx.statement(0)),
                ctx.statement(1) == null ? null : visitStatement(ctx.statement(1)), ctx.start.getLine(),
                ctx.start.getCharPositionInLine());
    }

    @Override
    public Statement visitCall(SimpLanPlusParser.CallContext ctx) {
        List<Exp> exps = ctx.exp().stream().map(p -> (Exp) visit(p)).collect(Collectors.toList());
        return new CallStmt(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.ID().getText(), exps);
    }

    @Override
    public Exp visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) {
        return new BaseExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Exp) visit(ctx.exp()));
    }

    @Override
    public BinExp visitBinExp(SimpLanPlusParser.BinExpContext ctx) {
        Exp left = (Exp) visit(ctx.left);
        Exp right = (Exp) visit(ctx.right);
        switch (ctx.op.getText()) {
            case "+":
                return new SumExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "-":
                return new SubExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "*":
                return new MultExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "/":
                return new DivExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "<":
                return new LessThanExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "<=":
                return new LessThanEqExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case ">":
                return new GreaterThanExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case ">=":
                return new GreaterThanEqExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "==":
                return new EqualExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "!=":
                return new NotEqualExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "&&":
                return new AndExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            case "||":
                return new OrExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), right, left);
            default:
                return null;
        }
    }

    @Override
    public Exp visitDerExp(SimpLanPlusParser.DerExpContext ctx) {
        return (Exp) visitLhs(ctx.lhs());
    }

    @Override
    public Exp visitNewExp(SimpLanPlusParser.NewExpContext ctx) {
        return new NewExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Type) visit(ctx.type()));
    }

    @Override
    public Exp visitValExp(SimpLanPlusParser.ValExpContext ctx) {
        return new ValExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public Exp visitNegExp(SimpLanPlusParser.NegExpContext ctx) {
        return new NegExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Exp) visit(ctx.exp()));
    }

    @Override
    public Exp visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) {
        return new BoolExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                Boolean.parseBoolean(ctx.BOOL().getText()));
    }

    @Override
    public Exp visitCallExp(SimpLanPlusParser.CallExpContext ctx) {
        return (Exp) visit(ctx.call());
    }

    @Override
    public Exp visitNotExp(SimpLanPlusParser.NotExpContext ctx) {
        return new NotExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Exp) visit(ctx.exp()));
    }
}