package com.unibo.ci.main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

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
	private final static Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());
	private static SyntaxErrorListener parserErrorsListener;

	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		ConsoleHandler handler = new ConsoleHandler();
		Formatter formatter = new LoggerFormatter();
		handler.setFormatter(formatter);
		LOGGER.addHandler(handler);

		if (args.length < 1) {
			LOGGER.severe("Missing input file");

			System.exit(1);
		} else {
			GlobalConfig.INPUT_FILENAME = args[0];
		}
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "--help":
				case "-h":
					System.out.println("SimpLanPlus compiler");
					System.out.println("INSERIRE COMANDO TODO");
					System.out.println("\t--ast, -a\t\tPrint abstract syntax three");
					System.out.println("\t--comments, -c\t\tPrint comments on assembly code");
					System.out.println("\t--out, -o\t\tSpecify out filename (default out)");
					break;
				case "--ast":
				case "-a":
					GlobalConfig.PRINT_AST = true;
					break;
				case "--comments":
				case "-c":
					GlobalConfig.PRINT_COMMENTS = true;
					break;
				case "--mem":
				case "-m":
					GlobalConfig.SHOW_MEM = true;
					break;
				case "--debug":
				case "-d":
					GlobalConfig.SHOW_DEBUG = true;
					break;
				case "--out":
				case "-o":
					if ((i + 1) < args.length)
						GlobalConfig.OUT_FILENAME = args[++i];
					break;
			}
		}
		CharStream codePointCharStream = null;
		try {
			codePointCharStream = CharStreams.fromFileName(GlobalConfig.INPUT_FILENAME);
		} catch (IOException e) {
			LOGGER.severe("File " + GlobalConfig.INPUT_FILENAME + " not exist 😡");
			System.exit(1);
		}
		SimpLanPlusLexer lexer = new SimpLanPlusLexer(codePointCharStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpLanPlusParser parser = new SimpLanPlusParser(tokens);

		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parserErrorsListener = new SyntaxErrorListener(LOGGER);
		parser.addErrorListener(parserErrorsListener);
		// Tell the parser to build the AST
		parser.setBuildParseTree(true);

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
				LOGGER.severe("SEMANTIC ERROR [" + semnErr.row + ", " + semnErr.col + "]: " + semnErr.desc);
			});
			return;
		}

		/* Check Type */
		ast.typeCheck();
		if (TypeErrorsStorage.getErrorList().size() > 0) {
			TypeErrorsStorage.getErrorList().forEach(typeErr -> {
				LOGGER.severe("TYPE ERROR [" + typeErr.row + ", " + typeErr.col + "]: " + typeErr.desc);
			});
			return;
		}

		TypeErrorsStorage.getErrorList().clear();

		/* Check Effects */
		SigmaEnv effects_env = new SigmaEnv();
		ArrayList<EffectError> effectsErrors = ast.AnalyzeEffect(effects_env);
		if (effectsErrors.size() > 0) {
			effectsErrors.forEach(effectErr -> {
				LOGGER.severe("EFFECT ERROR [" + effectErr.row + ", " + effectErr.col + "]: " + effectErr.desc);
			});
			return;
		}
		WarningsStorage warnings = new WarningsStorage(LOGGER);
		WarningsStorage.printAll();
		WarningsStorage.clear();

		String generatedCode = ast.codeGeneration();
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(GlobalConfig.OUT_FILENAME));
			out.write(generatedCode);
			out.close();
		} catch (IOException e1) {
			LOGGER.severe("Error writing file [" + GlobalConfig.OUT_FILENAME + "] 😡");
		}

		SVMLexer lexerASM = new SVMLexer(CharStreams.fromString(generatedCode));
		CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
		SVMParser parserASM = new SVMParser(tokensASM);

		SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
		visitorSVM.visit(parserASM.assembly());

		if (lexerASM.errorCount() > 0 || parserASM.getNumberOfSyntaxErrors() > 0) {
			LOGGER.info("Syntax errors: " + parserASM.getNumberOfSyntaxErrors());
			LOGGER.info("Lexical errors: " + lexerASM.errorCount());
			System.exit(1);
		}

		LOGGER.info("Starting SVM");

		SVM vm = new SVM(300, visitorSVM.getCode());
		try {
			vm.run();
		} catch (MemoryAccessException e) {
			LOGGER.severe("Invalid memory access");
		}

	}
}

class LoggerFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder builder = new StringBuilder();
		builder.append(record.getLevel() + ": ");
		builder.append(formatMessage(record));
		builder.append(System.lineSeparator());
		return builder.toString();
	}

}