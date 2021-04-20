package main;

import ast.SimpLanPlusVisitorImpl;
import parser.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;

public class Main
{
	public static void main( String[] args) throws Exception 
	{
		String fileName = "prova.slp";

		FileInputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);

		SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
		ParseTree tree = parser.block(); // begin parsing at rule 'prog'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
		Object ast = visitor.visit(tree); // Generazione AST
		
		//fase 1: stampare errori lessicali - scoprire come si può fare con antlr in automatico
		//fase 2: stampare errori semantici - fare a mano nodi dell'AST oppure scoprire come farlo in automatico
		//fase 3: controllo dei tipi ???
		
		
	}
}