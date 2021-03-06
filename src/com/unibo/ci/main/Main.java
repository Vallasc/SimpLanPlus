package com.unibo.ci.main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.util.logging.Logger;

import com.unibo.ci.ast.*;
import com.unibo.ci.ast.types.*;
import com.unibo.ci.listeners.SyntaxErrorListener;
import com.unibo.ci.parser.*;
import com.unibo.ci.util.Environment;

public class Main
{
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
	private static SyntaxErrorListener parserErrorsListener;

	public static void main( String[] args) throws Exception
	{
		String fileName = "prova.slp";

		FileInputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);

		SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpLanPlusParser parser = new SimpLanPlusParser(tokens);

		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parserErrorsListener = new SyntaxErrorListener(LOGGER);
		parser.addErrorListener(parserErrorsListener);
		// Tell the parser to build the AST
		//parser.setBuildParseTree(true);

		ParseTree tree = parser.block(); // begin parsing at rule 'block'

		if(parserErrorsListener.errorsDetected())
			System.exit(-1);

		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
		Node ast = visitor.visit(tree); // Generazione AST
		Environment env = new Environment();
		
		ast.checkSemantics(env).forEach(semnErr -> {
			System.out.println("Errore semantico trovato a " + semnErr.row + ", " + semnErr.col + ": " + semnErr.desc);
		});
		
		System.out.println("Programma terminato");
		
		
		
		//fase 1: stampare errori lessicali - scoprire come si può fare con antlr in automatico
		//fase 2: stampare errori semantici - fare a mano nodi dell'AST oppure scoprire come farlo in automatico
		//fase 3: controllo dei tipi ???

		//TODO se usiamo una variabile non inizializzata, cosa permessa dalla grammatica, il programma crasha
		//vedi prova.slp
		

		/*Type a = new TypePointer(new TypePointer(new TypePointer(new TypeInt())));
		Type b = new TypePointer(new TypePointer(new TypeInt()));
		Type c = new TypePointer(new TypePointer(new TypeInt()));
		LOGGER.info(a.equals(b) + "");*/

		
	}
}



