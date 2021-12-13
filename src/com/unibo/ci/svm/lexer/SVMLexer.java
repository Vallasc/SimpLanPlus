package com.unibo.ci.svm.lexer;

import java.util.List;
import java.util.ArrayList;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class SVMLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
			T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
			T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
			T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, LABEL = 29, NUMBER = 30, REGISTER = 31,
			WS = 32, LINECOMMENTS = 33, ERR = 34;
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
			"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8",
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16",
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24",
			"T__25", "T__26", "T__27", "LABEL", "NUMBER", "REGISTER", "WS", "LINECOMMENTS",
			"ERR"
	};

	private static final String[] _LITERAL_NAMES = {
			null, "'push'", "'pop'", "'lw'", "'('", "')'", "'sw'", "'li'", "'mv'",
			"'add'", "'sub'", "'mul'", "'div'", "'addi'", "'subi'", "'muli'", "'divi'",
			"'and'", "'or'", "'not'", "'beq'", "'bleq'", "'b'", "':'", "'jal'", "'jr'",
			"'del'", "'print'", "'halt'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, "LABEL", "NUMBER", "REGISTER", "WS", "LINECOMMENTS",
			"ERR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	private List<String> errors = new ArrayList<>();

	public int errorCount() {
		return errors.size();
	}

	public SVMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "SVM.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
			case 33:
				ERR_action((RuleContext) _localctx, actionIndex);
				break;
		}
	}

	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 0:
				errors.add("Invalid character: " + getText());
				break;
		}
	}

	public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u00f5\b\1\4\2\t"
			+
			"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
			"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
			"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
			"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
			"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5" +
			"\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13" +
			"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3" +
			"\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3" +
			"\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3" +
			"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3" +
			"\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3" +
			"\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\7\36\u00b5\n\36\f\36\16\36\u00b8" +
			"\13\36\3\37\3\37\5\37\u00bc\n\37\3\37\3\37\7\37\u00c0\n\37\f\37\16\37" +
			"\u00c3\13\37\5\37\u00c5\n\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3" +
			" \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u00df\n \3!\6!\u00e2\n!\r!\16!\u00e3" +
			"\3!\3!\3\"\3\"\7\"\u00ea\n\"\f\"\16\"\u00ed\13\"\3\"\3\"\3#\3#\3#\3#\3" +
			"#\2\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17" +
			"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35" +
			"9\36;\37= ?!A\"C#E$\3\2\6\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\4\2" +
			"\f\f\17\17\2\u0101\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13" +
			"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2" +
			"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2" +
			"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3" +
			"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2" +
			"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E" +
			"\3\2\2\2\3G\3\2\2\2\5L\3\2\2\2\7P\3\2\2\2\tS\3\2\2\2\13U\3\2\2\2\rW\3" +
			"\2\2\2\17Z\3\2\2\2\21]\3\2\2\2\23`\3\2\2\2\25d\3\2\2\2\27h\3\2\2\2\31" +
			"l\3\2\2\2\33p\3\2\2\2\35u\3\2\2\2\37z\3\2\2\2!\177\3\2\2\2#\u0084\3\2" +
			"\2\2%\u0088\3\2\2\2\'\u008b\3\2\2\2)\u008f\3\2\2\2+\u0093\3\2\2\2-\u0098" +
			"\3\2\2\2/\u009a\3\2\2\2\61\u009c\3\2\2\2\63\u00a0\3\2\2\2\65\u00a3\3\2" +
			"\2\2\67\u00a7\3\2\2\29\u00ad\3\2\2\2;\u00b2\3\2\2\2=\u00c4\3\2\2\2?\u00de" +
			"\3\2\2\2A\u00e1\3\2\2\2C\u00e7\3\2\2\2E\u00f0\3\2\2\2GH\7r\2\2HI\7w\2" +
			"\2IJ\7u\2\2JK\7j\2\2K\4\3\2\2\2LM\7r\2\2MN\7q\2\2NO\7r\2\2O\6\3\2\2\2" +
			"PQ\7n\2\2QR\7y\2\2R\b\3\2\2\2ST\7*\2\2T\n\3\2\2\2UV\7+\2\2V\f\3\2\2\2" +
			"WX\7u\2\2XY\7y\2\2Y\16\3\2\2\2Z[\7n\2\2[\\\7k\2\2\\\20\3\2\2\2]^\7o\2" +
			"\2^_\7x\2\2_\22\3\2\2\2`a\7c\2\2ab\7f\2\2bc\7f\2\2c\24\3\2\2\2de\7u\2" +
			"\2ef\7w\2\2fg\7d\2\2g\26\3\2\2\2hi\7o\2\2ij\7w\2\2jk\7n\2\2k\30\3\2\2" +
			"\2lm\7f\2\2mn\7k\2\2no\7x\2\2o\32\3\2\2\2pq\7c\2\2qr\7f\2\2rs\7f\2\2s" +
			"t\7k\2\2t\34\3\2\2\2uv\7u\2\2vw\7w\2\2wx\7d\2\2xy\7k\2\2y\36\3\2\2\2z" +
			"{\7o\2\2{|\7w\2\2|}\7n\2\2}~\7k\2\2~ \3\2\2\2\177\u0080\7f\2\2\u0080\u0081" +
			"\7k\2\2\u0081\u0082\7x\2\2\u0082\u0083\7k\2\2\u0083\"\3\2\2\2\u0084\u0085" +
			"\7c\2\2\u0085\u0086\7p\2\2\u0086\u0087\7f\2\2\u0087$\3\2\2\2\u0088\u0089" +
			"\7q\2\2\u0089\u008a\7t\2\2\u008a&\3\2\2\2\u008b\u008c\7p\2\2\u008c\u008d" +
			"\7q\2\2\u008d\u008e\7v\2\2\u008e(\3\2\2\2\u008f\u0090\7d\2\2\u0090\u0091" +
			"\7g\2\2\u0091\u0092\7s\2\2\u0092*\3\2\2\2\u0093\u0094\7d\2\2\u0094\u0095" +
			"\7n\2\2\u0095\u0096\7g\2\2\u0096\u0097\7s\2\2\u0097,\3\2\2\2\u0098\u0099" +
			"\7d\2\2\u0099.\3\2\2\2\u009a\u009b\7<\2\2\u009b\60\3\2\2\2\u009c\u009d" +
			"\7l\2\2\u009d\u009e\7c\2\2\u009e\u009f\7n\2\2\u009f\62\3\2\2\2\u00a0\u00a1" +
			"\7l\2\2\u00a1\u00a2\7t\2\2\u00a2\64\3\2\2\2\u00a3\u00a4\7f\2\2\u00a4\u00a5" +
			"\7g\2\2\u00a5\u00a6\7n\2\2\u00a6\66\3\2\2\2\u00a7\u00a8\7r\2\2\u00a8\u00a9" +
			"\7t\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7v\2\2\u00ac" +
			"8\3\2\2\2\u00ad\u00ae\7j\2\2\u00ae\u00af\7c\2\2\u00af\u00b0\7n\2\2\u00b0" +
			"\u00b1\7v\2\2\u00b1:\3\2\2\2\u00b2\u00b6\t\2\2\2\u00b3\u00b5\t\3\2\2\u00b4" +
			"\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2" +
			"\2\2\u00b7<\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00c5\7\62\2\2\u00ba\u00bc" +
			"\7/\2\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd" +
			"\u00c1\4\63;\2\u00be\u00c0\4\62;\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2" +
			"\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3" +
			"\u00c1\3\2\2\2\u00c4\u00b9\3\2\2\2\u00c4\u00bb\3\2\2\2\u00c5>\3\2\2\2" +
			"\u00c6\u00c7\7&\2\2\u00c7\u00c8\7c\2\2\u00c8\u00df\7\62\2\2\u00c9\u00ca" +
			"\7&\2\2\u00ca\u00cb\7v\2\2\u00cb\u00df\7\63\2\2\u00cc\u00cd\7&\2\2\u00cd" +
			"\u00ce\7u\2\2\u00ce\u00df\7r\2\2\u00cf\u00d0\7&\2\2\u00d0\u00d1\7h\2\2" +
			"\u00d1\u00df\7r\2\2\u00d2\u00d3\7&\2\2\u00d3\u00d4\7c\2\2\u00d4\u00df" +
			"\7n\2\2\u00d5\u00d6\7&\2\2\u00d6\u00d7\7t\2\2\u00d7\u00df\7c\2\2\u00d8" +
			"\u00d9\7&\2\2\u00d9\u00da\7j\2\2\u00da\u00df\7r\2\2\u00db\u00dc\7&\2\2" +
			"\u00dc\u00dd\7e\2\2\u00dd\u00df\7n\2\2\u00de\u00c6\3\2\2\2\u00de\u00c9" +
			"\3\2\2\2\u00de\u00cc\3\2\2\2\u00de\u00cf\3\2\2\2\u00de\u00d2\3\2\2\2\u00de" +
			"\u00d5\3\2\2\2\u00de\u00d8\3\2\2\2\u00de\u00db\3\2\2\2\u00df@\3\2\2\2" +
			"\u00e0\u00e2\t\4\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e1" +
			"\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\b!\2\2\u00e6" +
			"B\3\2\2\2\u00e7\u00eb\7=\2\2\u00e8\u00ea\n\5\2\2\u00e9\u00e8\3\2\2\2\u00ea" +
			"\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2" +
			"\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00ef\b\"\3\2\u00efD\3\2\2\2\u00f0\u00f1" +
			"\13\2\2\2\u00f1\u00f2\b#\4\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\b#\2\2\u00f4" +
			"F\3\2\2\2\n\2\u00b6\u00bb\u00c1\u00c4\u00de\u00e3\u00eb\5\2\3\2\b\2\2" +
			"\3#\2";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}