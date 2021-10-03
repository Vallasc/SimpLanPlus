package com.unibo.ci.ast;

import java.util.List;
import java.util.stream.Collectors;

import com.unibo.ci.ast.dec.*;
import com.unibo.ci.ast.exp.*;
import com.unibo.ci.ast.stmt.*;
import com.unibo.ci.ast.stmt.block.*;
import com.unibo.ci.ast.types.*;
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
        List<Arg> args = ctx.arg().stream().map(this::visitArg).collect(Collectors.toList());
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
    public Exp visitCall(SimpLanPlusParser.CallContext ctx) {
        List<Exp> exps = ctx.exp().stream().map(p -> (Exp) visit(p)).collect(Collectors.toList());
        return new CallStmt(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.ID().getText(), exps );
    }

    @Override 
    public Exp visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) { 
        return new BaseExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Exp) visit(ctx.exp())); 
    }

    @Override 
    public Exp visitBinExp(SimpLanPlusParser.BinExpContext ctx) { 
		Exp left = (Exp) visit(ctx.left);
		Exp right = (Exp) visit(ctx.right);
		switch (ctx.op.getText()) {
		case "+":
			return new ExpSum(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "-":
			return new ExpSub(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "*":
			return new ExpMult(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "/":
			return new ExpDiv(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "<":
			return new ExpLessThan(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "<=":
			return new ExpLessThanEq(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case ">":
			return new ExpGreaterThan(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case ">=":
			return new ExpGreaterThanEq(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "==":
			return new ExpEqual(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "!=":
			return new ExpNotEqual(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "&&":
			return new ExpAnd(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "||":
			return new ExpOr(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		default:
			return null;
		}
    }

    @Override 
    public Exp visitDerExp(SimpLanPlusParser.DerExpContext ctx) {
        return new DerExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), 
                            ctx.lhs() != null ? (Exp) visit(ctx.lhs()) : null); 
    }

    @Override 
    public Exp visitNewExp(SimpLanPlusParser.NewExpContext ctx) { 
        return new NewExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Type) visit(ctx.type())); 
    }

    @Override 
    public Exp visitValExp(SimpLanPlusParser.ValExpContext ctx) { 
        return new ValExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), Integer.parseInt(ctx.NUMBER().getText())); 
    }

    @Override 
    public Exp visitNegExp(SimpLanPlusParser.NegExpContext ctx) { 
        return new NegExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), (Exp) visit(ctx.exp())); 
    }

    @Override 
    public Exp visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) {
        return new BoolExp(ctx.start.getLine(), ctx.start.getCharPositionInLine(), Boolean.parseBoolean(ctx.BOOL().getText())); 
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