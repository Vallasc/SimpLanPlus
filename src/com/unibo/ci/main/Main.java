package com.unibo.ci.main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.util.logging.Logger;

import com.unibo.ci.ast.*;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.listeners.SyntaxErrorListener;
import com.unibo.ci.parser.*;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;

public class Main {
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
	private static SyntaxErrorListener parserErrorsListener;

	public static void main(String[] args) throws Exception {
		// String fileName = "test/test_fun_ite_return.slp";
		String fileName = "test/eff3ct_an4lysus.slp";

		FileInputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);

		SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpLanPlusParser parser = new SimpLanPlusParser(tokens);

		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parserErrorsListener = new SyntaxErrorListener(LOGGER);
		parser.addErrorListener(parserErrorsListener);
		// Tell the parser to build the AST
		// parser.setBuildParseTree(true);

		ParseTree tree = parser.block(); // begin parsing at rule 'block'

		if (parserErrorsListener.errorsDetected())
			System.exit(-1);

		LOGGER.info(tree.toStringTree(parser)); // print LISP-style tre

		SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
		Node ast = visitor.visit(tree); // Generazione AST

		System.out.println("AST three: \n" + ast.toPrint("\t"));

		GammaEnv env = new GammaEnv();
		SigmaEnv effects_env = new SigmaEnv();

		ast.checkSemantics(env).forEach(semnErr -> {
			System.out.println("Semantic error " + semnErr.row + ", " + semnErr.col + ": " + semnErr.desc);
		});
		ast.typeCheck();
		TypeErrorsStorage.getErrorList().forEach(typeErr -> {
			System.out.println("Type error " + typeErr.row + ", " + typeErr.col + ": " + typeErr.desc);
		});
		
		if (ast.AnalyzeEffect(effects_env) != null) // TODO togli questo if - tutti gli analyze effect devono restituire
													// una lista != null
			ast.AnalyzeEffect(effects_env).forEach(effectErr -> {
				System.out.println("Effect error " + effectErr.row + ", " + effectErr.col + ": " + effectErr.desc);
			});
		else {
			System.out.println("Non ho trovato errori sugli effetti");
		}

		System.out.println("Programma terminato");

		// fase 1: stampare errori lessicali - scoprire come si può fare con antlr in
		// automatico
		// fase 2: stampare errori semantici - fare a mano nodi dell'AST oppure scoprire
		// come farlo in automatico
		// fase 3: controllo dei tipi ???

		// TODO se usiamo una variabile non inizializzata, cosa permessa dalla
		// grammatica, il programma crasha
		// vedi prova.slp

		/*
		 * Type a = new TypePointer(new TypePointer(new TypePointer(new TypeInt())));
		 * Type b = new TypePointer(new TypePointer(new TypeInt())); Type c = new
		 * TypePointer(new TypePointer(new TypeInt())); LOGGER.info(a.equals(b) + "");
		 */

	}
}
