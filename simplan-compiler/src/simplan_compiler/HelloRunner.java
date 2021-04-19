package simplan_compiler;
import parser.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class HelloRunner 
{
	public static void main( String[] args) throws Exception 
	{

		//ANTLRInputStream input = new ANTLRInputStream( System.in);
		ANTLRInputStream input = new ANTLRInputStream("hello there");

		SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
		
		

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
		ParseTree tree = parser.block(); // begin parsing at rule 'prog'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		
		//fase 1: stampare errori lessicali - scoprire come si può fare con antlr in automatico
		//fase 2: stampare errori semantici - fare a mano nodi dell'AST oppure scoprire come farlo in automatico
		//fase 3: controllo dei tipi ???
		
		
	}
}