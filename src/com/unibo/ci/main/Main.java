package com.unibo.ci.main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.unibo.ci.ast.*;
import com.unibo.ci.ast.errors.EffectError;
import com.unibo.ci.ast.errors.SemanticError;
import com.unibo.ci.listeners.SyntaxErrorListener;
import com.unibo.ci.parser.*;
import com.unibo.ci.svm.SVM;
import com.unibo.ci.svm.SVMVisitorImpl;
import com.unibo.ci.svm.SVM.MemoryAccessException;
import com.unibo.ci.svm.lexer.SVMLexer;
import com.unibo.ci.svm.lexer.SVMParser;
import com.unibo.ci.util.GammaEnv;
import com.unibo.ci.util.GlobalConfig;
import com.unibo.ci.util.SigmaEnv;
import com.unibo.ci.util.TypeErrorsStorage;
import com.unibo.ci.util.WarningsStorage;

public class Main {
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
	private static SyntaxErrorListener parserErrorsListener;

	public static void main(String[] args) {

		String fileName = "./test/test1.slp";
		if (args.length != 1)
			LOGGER.info("WOOOOO INSERISCI IN FILE SORGENTE WOOOOOO");
		else
			fileName = args[0];

		ANTLRInputStream input;
		try {
			FileInputStream is = new FileInputStream(fileName);
			input = new ANTLRInputStream(is);
		} catch (IOException e) {
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

		// LOGGER.info(tree.toStringTree(parser)); // print LISP-style tre

		SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
		Node ast = visitor.visit(tree); // Generazione AST

		if (GlobalConfig.PRINT_AST)
			System.out.println("AST three: \n" + ast.toPrint("\t"));

		/* Check Semantics */
		GammaEnv env = new GammaEnv();
		ArrayList<SemanticError> semanticErrors = ast.checkSemantics(env);
		if (semanticErrors.size() > 0) {
			semanticErrors.forEach(semnErr -> {
				LOGGER.info("Semantic error " + semnErr.row + ", " + semnErr.col + ": " + semnErr.desc);
			});
			return;
		}

		/* Check Type */
		ast.typeCheck();
		if (TypeErrorsStorage.getErrorList().size() > 0) {
			TypeErrorsStorage.getErrorList().forEach(typeErr -> {
				LOGGER.info("Type error " + typeErr.row + ", " + typeErr.col + ": " + typeErr.desc);
			});
			return;
		}
		TypeErrorsStorage.getErrorList().clear();

		/* Check Effects */
		SigmaEnv effects_env = new SigmaEnv();
		ArrayList<EffectError> effectsErrors = ast.AnalyzeEffect(effects_env);
		if (effectsErrors.size() > 0) {
			effectsErrors.forEach(effectErr -> {
				LOGGER.info("Effect error " + effectErr.row + ", " + effectErr.col + ": " + effectErr.desc);
			});
			return;
		}

		WarningsStorage.printAll();
		WarningsStorage.clear();

		String code = ast.codeGeneration();
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("prova.asm"));
			out.write(code);
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			FileInputStream isASM = new FileInputStream("prova.asm");
			ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
			SVMLexer lexerASM = new SVMLexer(inputASM);
			CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
			SVMParser parserASM = new SVMParser(tokensASM);

			// parserASM.assembly();

			SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
			visitorSVM.visit(parserASM.assembly());

			System.out.println("You had: " + lexerASM.errorCount() + " lexical errors and "
					+ parserASM.getNumberOfSyntaxErrors() + " syntax errors.");
			if (lexerASM.errorCount() > 0 || parserASM.getNumberOfSyntaxErrors() > 0)
				System.exit(1);

			System.out.println("Starting Virtual Machine...");

			SVM vm = new SVM(100, visitorSVM.getCode());
			try {
				vm.run();
			} catch (MemoryAccessException e) {
				System.out.println(e.getMessage());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
