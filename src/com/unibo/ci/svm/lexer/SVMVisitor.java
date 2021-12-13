package com.unibo.ci.svm.lexer;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SVMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface SVMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SVMParser#assembly}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssembly(SVMParser.AssemblyContext ctx);

	/**
	 * Visit a parse tree produced by the {@code push}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(SVMParser.PushContext ctx);

	/**
	 * Visit a parse tree produced by the {@code pop}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(SVMParser.PopContext ctx);

	/**
	 * Visit a parse tree produced by the {@code lw}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLw(SVMParser.LwContext ctx);

	/**
	 * Visit a parse tree produced by the {@code sw}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSw(SVMParser.SwContext ctx);

	/**
	 * Visit a parse tree produced by the {@code li}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLi(SVMParser.LiContext ctx);

	/**
	 * Visit a parse tree produced by the {@code mv}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMv(SVMParser.MvContext ctx);

	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(SVMParser.AddContext ctx);

	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(SVMParser.SubContext ctx);

	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(SVMParser.MulContext ctx);

	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(SVMParser.DivContext ctx);

	/**
	 * Visit a parse tree produced by the {@code addi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddi(SVMParser.AddiContext ctx);

	/**
	 * Visit a parse tree produced by the {@code subi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubi(SVMParser.SubiContext ctx);

	/**
	 * Visit a parse tree produced by the {@code muli}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuli(SVMParser.MuliContext ctx);

	/**
	 * Visit a parse tree produced by the {@code divi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivi(SVMParser.DiviContext ctx);

	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(SVMParser.AndContext ctx);

	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(SVMParser.OrContext ctx);

	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(SVMParser.NotContext ctx);

	/**
	 * Visit a parse tree produced by the {@code beq}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeq(SVMParser.BeqContext ctx);

	/**
	 * Visit a parse tree produced by the {@code bleq}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBleq(SVMParser.BleqContext ctx);

	/**
	 * Visit a parse tree produced by the {@code b}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(SVMParser.BContext ctx);

	/**
	 * Visit a parse tree produced by the {@code label}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(SVMParser.LabelContext ctx);

	/**
	 * Visit a parse tree produced by the {@code jal}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJal(SVMParser.JalContext ctx);

	/**
	 * Visit a parse tree produced by the {@code jr}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJr(SVMParser.JrContext ctx);

	/**
	 * Visit a parse tree produced by the {@code del}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDel(SVMParser.DelContext ctx);

	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SVMParser.PrintContext ctx);

	/**
	 * Visit a parse tree produced by the {@code halt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHalt(SVMParser.HaltContext ctx);
}