// Generated from SimpLan.g4 by ANTLR 4.9.2
package simplan_compiler;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpLanParser}.
 */
public interface SimpLanListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(SimpLanParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(SimpLanParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link SimpLanParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterLetInExp(SimpLanParser.LetInExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link SimpLanParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitLetInExp(SimpLanParser.LetInExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#let}.
	 * @param ctx the parse tree
	 */
	void enterLet(SimpLanParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#let}.
	 * @param ctx the parse tree
	 */
	void exitLet(SimpLanParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#vardec}.
	 * @param ctx the parse tree
	 */
	void enterVardec(SimpLanParser.VardecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#vardec}.
	 * @param ctx the parse tree
	 */
	void exitVardec(SimpLanParser.VardecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#varasm}.
	 * @param ctx the parse tree
	 */
	void enterVarasm(SimpLanParser.VarasmContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#varasm}.
	 * @param ctx the parse tree
	 */
	void exitVarasm(SimpLanParser.VarasmContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(SimpLanParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(SimpLanParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varAssignment}
	 * labeled alternative in {@link SimpLanParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterVarAssignment(SimpLanParser.VarAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varAssignment}
	 * labeled alternative in {@link SimpLanParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitVarAssignment(SimpLanParser.VarAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link SimpLanParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDeclaration(SimpLanParser.FunDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link SimpLanParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDeclaration(SimpLanParser.FunDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimpLanParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimpLanParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(SimpLanParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(SimpLanParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SimpLanParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SimpLanParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(SimpLanParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(SimpLanParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIntVal(SimpLanParser.IntValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIntVal(SimpLanParser.IntValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBoolVal(SimpLanParser.BoolValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBoolVal(SimpLanParser.BoolValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBaseExp(SimpLanParser.BaseExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBaseExp(SimpLanParser.BaseExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(SimpLanParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(SimpLanParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void enterVarExp(SimpLanParser.VarExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void exitVarExp(SimpLanParser.VarExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void enterFunExp(SimpLanParser.FunExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanParser#value}.
	 * @param ctx the parse tree
	 */
	void exitFunExp(SimpLanParser.FunExpContext ctx);
}