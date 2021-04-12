package simplan_compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class HelloRunner 
{
	public static void main( String[] args) throws Exception 
	{

		//ANTLRInputStream input = new ANTLRInputStream( System.in);
		ANTLRInputStream input = new ANTLRInputStream("hello there");

		SimpLanLexer lexer = new SimpLanLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		SimpLanParser parser = new SimpLanParser(tokens);
		ParseTree tree = parser.prog(); // begin parsing at rule 'prog'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	}
}