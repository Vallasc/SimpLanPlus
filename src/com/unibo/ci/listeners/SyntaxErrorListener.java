package com.unibo.ci.listeners;

import java.io.IOException;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;


/**
 * A syntax error listener.
 */
public class SyntaxErrorListener implements ANTLRErrorListener {

	private final Logger LOGGER;
	private boolean errors = false;

	/**
	 * @param logger
	 */
	public SyntaxErrorListener(Logger logger) {
		this.LOGGER = logger;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		if (LOGGER != null)
			LOGGER.log(Level.SEVERE, "SYNTAX ERROR: ["+ line +":"+ charPositionInLine +"] - "+ msg);
		errors = true;
	}

	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
			BitSet ambigAlts, ATNConfigSet configs) {
	}

	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
			BitSet conflictingAlts, ATNConfigSet configs) {
	}

	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
			ATNConfigSet configs) {
	}

	public boolean errorsDetected() {
		return errors;
	}

}
