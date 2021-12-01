package com.unibo.ci.main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.unibo.ci.ast.*;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.listeners.SyntaxErrorListener;
import com.unibo.ci.parser.*;
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.util.WarningsStorage;

public class Main {
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
	private static SyntaxErrorListener parserErrorsListener;

	public static void main(String[] args) {

		String fileName = "";
		if(args.length != 1)
			LOGGER.info("WOOOOO INSERISCI IN FILE SORGENTE WOOOOOO");
		else 
			fileName = args[0];

		ANTLRInputStream input;
		try {
			FileInputStream is = new FileInputStream(fileName);
	 		input = new ANTLRInputStream(is);
		} catch ( IOException e) {
			LOGGER.severe("File " + fileName + " not exist 😡");
			return;
		}

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
		
		//LOGGER.info(tree.toStringTree(parser)); // print LISP-style tre

		SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
		Node ast = visitor.visit(tree); // Generazione AST

		if(GlobalConfig.PRINT_AST)
			System.out.println("AST three: \n" + ast.toPrint("\t"));

		/* Check Semantics */
		GammaEnv env = new GammaEnv();
		ArrayList<SemanticError> semanticErrors = ast.checkSemantics(env);
		if(semanticErrors.size() > 0) {
			semanticErrors.forEach(semnErr -> {
				LOGGER.info("Semantic error " + semnErr.row + ", " + semnErr.col + ": " + semnErr.desc);
			});
			return;
		}

		/* Check Type */
		ast.typeCheck();
		if( TypeErrorsStorage.getErrorList().size() > 0 ) {
			TypeErrorsStorage.getErrorList().forEach(typeErr -> {
				LOGGER.info("Type error " + typeErr.row + ", " + typeErr.col + ": " + typeErr.desc);
			});
			return;
		}
		TypeErrorsStorage.getErrorList().clear();
		
		/* Check Effects */
		SigmaEnv effects_env = new SigmaEnv();
		ArrayList<EffectError> effectsErrors = ast.AnalyzeEffect(effects_env);
		if(effectsErrors.size() > 0) {
			effectsErrors.forEach(effectErr -> {
				LOGGER.info("Effect error " + effectErr.row + ", " + effectErr.col + ": " + effectErr.desc);
			});
			return;
		}
		
		WarningsStorage.printAll(); WarningsStorage.getWarningList().clear();

		System.out.println("Programma " + fileName + " terminato");

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
