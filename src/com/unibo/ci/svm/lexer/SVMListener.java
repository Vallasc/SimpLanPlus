// Generated from /home/simone/github/SimpLanPlus/src/com/unibo/ci/svm/lexer/SVM.g4 by ANTLR 4.9.2
package com.unibo.ci.svm.lexer;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SVMParser}.
 */
public interface SVMListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterAssembly(SVMParser.AssemblyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitAssembly(SVMParser.AssemblyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code push}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterPush(SVMParser.PushContext ctx);
	/**
	 * Exit a parse tree produced by the {@code push}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitPush(SVMParser.PushContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pop}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterPop(SVMParser.PopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pop}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitPop(SVMParser.PopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lw}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLw(SVMParser.LwContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lw}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLw(SVMParser.LwContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sw}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterSw(SVMParser.SwContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sw}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitSw(SVMParser.SwContext ctx);
	/**
	 * Enter a parse tree produced by the {@code li}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLi(SVMParser.LiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code li}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLi(SVMParser.LiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mv}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterMv(SVMParser.MvContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mv}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitMv(SVMParser.MvContext ctx);
	/**
	 * Enter a parse tree produced by the {@code add}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterAdd(SVMParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitAdd(SVMParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sub}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterSub(SVMParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitSub(SVMParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mul}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterMul(SVMParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitMul(SVMParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code div}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterDiv(SVMParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code div}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitDiv(SVMParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterAddi(SVMParser.AddiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitAddi(SVMParser.AddiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterSubi(SVMParser.SubiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitSubi(SVMParser.SubiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code muli}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterMuli(SVMParser.MuliContext ctx);
	/**
	 * Exit a parse tree produced by the {@code muli}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitMuli(SVMParser.MuliContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterDivi(SVMParser.DiviContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divi}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitDivi(SVMParser.DiviContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterAnd(SVMParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitAnd(SVMParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterOr(SVMParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitOr(SVMParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterNot(SVMParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitNot(SVMParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code beq}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterBeq(SVMParser.BeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code beq}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitBeq(SVMParser.BeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bleq}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterBleq(SVMParser.BleqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bleq}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitBleq(SVMParser.BleqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code b}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterB(SVMParser.BContext ctx);
	/**
	 * Exit a parse tree produced by the {@code b}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitB(SVMParser.BContext ctx);
	/**
	 * Enter a parse tree produced by the {@code label}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterLabel(SVMParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code label}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitLabel(SVMParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jal}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterJal(SVMParser.JalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jal}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitJal(SVMParser.JalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jr}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterJr(SVMParser.JrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jr}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitJr(SVMParser.JrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code del}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterDel(SVMParser.DelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code del}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitDel(SVMParser.DelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code print}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterPrint(SVMParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code print}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitPrint(SVMParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printchar}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterPrintchar(SVMParser.PrintcharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printchar}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitPrintchar(SVMParser.PrintcharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code halt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterHalt(SVMParser.HaltContext ctx);
	/**
	 * Exit a parse tree produced by the {@code halt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitHalt(SVMParser.HaltContext ctx);
}