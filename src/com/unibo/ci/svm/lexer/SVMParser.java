package com.unibo.ci.svm.lexer;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class SVMParser extends Parser {
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
	public static final int RULE_assembly = 0, RULE_instruction = 1;
	public static final String[] ruleNames = {
			"assembly", "instruction"
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
	public ATN getATN() {
		return _ATN;
	}

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class AssemblyContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}

		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class, i);
		}

		public AssemblyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_assembly;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitAssembly(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AssemblyContext assembly() throws RecognitionException {
		AssemblyContext _localctx = new AssemblyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assembly);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(7);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2)
						| (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10)
						| (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16)
						| (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__23)
						| (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << LABEL))) != 0)) {
					{
						{
							setState(4);
							instruction();
						}
					}
					setState(9);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_instruction;
		}

		public InstructionContext() {
		}

		public void copyFrom(InstructionContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class SubContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public SubContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitSub(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class MulContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public MulContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitMul(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class AddiContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public TerminalNode NUMBER() {
			return getToken(SVMParser.NUMBER, 0);
		}

		public AddiContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitAddi(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class JrContext extends InstructionContext {
		public TerminalNode REGISTER() {
			return getToken(SVMParser.REGISTER, 0);
		}

		public JrContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitJr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class DelContext extends InstructionContext {
		public TerminalNode REGISTER() {
			return getToken(SVMParser.REGISTER, 0);
		}

		public DelContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitDel(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LwContext extends InstructionContext {
		public Token out;
		public Token offset;
		public Token in;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public TerminalNode NUMBER() {
			return getToken(SVMParser.NUMBER, 0);
		}

		public LwContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitLw(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class BeqContext extends InstructionContext {
		public Token in;
		public Token in2;

		public TerminalNode LABEL() {
			return getToken(SVMParser.LABEL, 0);
		}

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public BeqContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitBeq(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class PopContext extends InstructionContext {
		public PopContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitPop(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class DivContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public DivContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitDiv(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class NotContext extends InstructionContext {
		public Token out;
		public Token in;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public NotContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitNot(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class JalContext extends InstructionContext {
		public TerminalNode LABEL() {
			return getToken(SVMParser.LABEL, 0);
		}

		public JalContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitJal(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class MuliContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public TerminalNode NUMBER() {
			return getToken(SVMParser.NUMBER, 0);
		}

		public MuliContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitMuli(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class AndContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public AndContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitAnd(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class DiviContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public TerminalNode NUMBER() {
			return getToken(SVMParser.NUMBER, 0);
		}

		public DiviContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitDivi(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class BleqContext extends InstructionContext {
		public Token in;
		public Token in2;

		public TerminalNode LABEL() {
			return getToken(SVMParser.LABEL, 0);
		}

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public BleqContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitBleq(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class SubiContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public TerminalNode NUMBER() {
			return getToken(SVMParser.NUMBER, 0);
		}

		public SubiContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitSubi(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class AddContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public AddContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitAdd(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class BContext extends InstructionContext {
		public TerminalNode LABEL() {
			return getToken(SVMParser.LABEL, 0);
		}

		public BContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitB(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class OrContext extends InstructionContext {
		public Token out;
		public Token in;
		public Token in2;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public OrContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitOr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class SwContext extends InstructionContext {
		public Token in;
		public Token offset;
		public Token out;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public TerminalNode NUMBER() {
			return getToken(SVMParser.NUMBER, 0);
		}

		public SwContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitSw(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class MvContext extends InstructionContext {
		public Token out;
		public Token in;

		public List<TerminalNode> REGISTER() {
			return getTokens(SVMParser.REGISTER);
		}

		public TerminalNode REGISTER(int i) {
			return getToken(SVMParser.REGISTER, i);
		}

		public MvContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitMv(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LabelContext extends InstructionContext {
		public TerminalNode LABEL() {
			return getToken(SVMParser.LABEL, 0);
		}

		public LabelContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitLabel(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class PushContext extends InstructionContext {
		public TerminalNode REGISTER() {
			return getToken(SVMParser.REGISTER, 0);
		}

		public PushContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitPush(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class HaltContext extends InstructionContext {
		public HaltContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitHalt(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class PrintContext extends InstructionContext {
		public TerminalNode REGISTER() {
			return getToken(SVMParser.REGISTER, 0);
		}

		public PrintContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitPrint(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LiContext extends InstructionContext {
		public Token out;
		public Token in;

		public TerminalNode REGISTER() {
			return getToken(SVMParser.REGISTER, 0);
		}

		public TerminalNode NUMBER() {
			return getToken(SVMParser.NUMBER, 0);
		}

		public LiContext(InstructionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof SVMVisitor)
				return ((SVMVisitor<? extends T>) visitor).visitLi(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case T__0:
					_localctx = new PushContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(10);
					match(T__0);
					setState(11);
					match(REGISTER);
				}
					break;
				case T__1:
					_localctx = new PopContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(12);
					match(T__1);
				}
					break;
				case T__2:
					_localctx = new LwContext(_localctx);
					enterOuterAlt(_localctx, 3); {
					setState(13);
					match(T__2);
					setState(14);
					((LwContext) _localctx).out = match(REGISTER);
					setState(15);
					((LwContext) _localctx).offset = match(NUMBER);
					setState(16);
					match(T__3);
					setState(17);
					((LwContext) _localctx).in = match(REGISTER);
					setState(18);
					match(T__4);
				}
					break;
				case T__5:
					_localctx = new SwContext(_localctx);
					enterOuterAlt(_localctx, 4); {
					setState(19);
					match(T__5);
					setState(20);
					((SwContext) _localctx).in = match(REGISTER);
					setState(21);
					((SwContext) _localctx).offset = match(NUMBER);
					setState(22);
					match(T__3);
					setState(23);
					((SwContext) _localctx).out = match(REGISTER);
					setState(24);
					match(T__4);
				}
					break;
				case T__6:
					_localctx = new LiContext(_localctx);
					enterOuterAlt(_localctx, 5); {
					setState(25);
					match(T__6);
					setState(26);
					((LiContext) _localctx).out = match(REGISTER);
					setState(27);
					((LiContext) _localctx).in = match(NUMBER);
				}
					break;
				case T__7:
					_localctx = new MvContext(_localctx);
					enterOuterAlt(_localctx, 6); {
					setState(28);
					match(T__7);
					setState(29);
					((MvContext) _localctx).out = match(REGISTER);
					setState(30);
					((MvContext) _localctx).in = match(REGISTER);
				}
					break;
				case T__8:
					_localctx = new AddContext(_localctx);
					enterOuterAlt(_localctx, 7); {
					setState(31);
					match(T__8);
					setState(32);
					((AddContext) _localctx).out = match(REGISTER);
					setState(33);
					((AddContext) _localctx).in = match(REGISTER);
					setState(34);
					((AddContext) _localctx).in2 = match(REGISTER);
				}
					break;
				case T__9:
					_localctx = new SubContext(_localctx);
					enterOuterAlt(_localctx, 8); {
					setState(35);
					match(T__9);
					setState(36);
					((SubContext) _localctx).out = match(REGISTER);
					setState(37);
					((SubContext) _localctx).in = match(REGISTER);
					setState(38);
					((SubContext) _localctx).in2 = match(REGISTER);
				}
					break;
				case T__10:
					_localctx = new MulContext(_localctx);
					enterOuterAlt(_localctx, 9); {
					setState(39);
					match(T__10);
					setState(40);
					((MulContext) _localctx).out = match(REGISTER);
					setState(41);
					((MulContext) _localctx).in = match(REGISTER);
					setState(42);
					((MulContext) _localctx).in2 = match(REGISTER);
				}
					break;
				case T__11:
					_localctx = new DivContext(_localctx);
					enterOuterAlt(_localctx, 10); {
					setState(43);
					match(T__11);
					setState(44);
					((DivContext) _localctx).out = match(REGISTER);
					setState(45);
					((DivContext) _localctx).in = match(REGISTER);
					setState(46);
					((DivContext) _localctx).in2 = match(REGISTER);
				}
					break;
				case T__12:
					_localctx = new AddiContext(_localctx);
					enterOuterAlt(_localctx, 11); {
					setState(47);
					match(T__12);
					setState(48);
					((AddiContext) _localctx).out = match(REGISTER);
					setState(49);
					((AddiContext) _localctx).in = match(REGISTER);
					setState(50);
					((AddiContext) _localctx).in2 = match(NUMBER);
				}
					break;
				case T__13:
					_localctx = new SubiContext(_localctx);
					enterOuterAlt(_localctx, 12); {
					setState(51);
					match(T__13);
					setState(52);
					((SubiContext) _localctx).out = match(REGISTER);
					setState(53);
					((SubiContext) _localctx).in = match(REGISTER);
					setState(54);
					((SubiContext) _localctx).in2 = match(NUMBER);
				}
					break;
				case T__14:
					_localctx = new MuliContext(_localctx);
					enterOuterAlt(_localctx, 13); {
					setState(55);
					match(T__14);
					setState(56);
					((MuliContext) _localctx).out = match(REGISTER);
					setState(57);
					((MuliContext) _localctx).in = match(REGISTER);
					setState(58);
					((MuliContext) _localctx).in2 = match(NUMBER);
				}
					break;
				case T__15:
					_localctx = new DiviContext(_localctx);
					enterOuterAlt(_localctx, 14); {
					setState(59);
					match(T__15);
					setState(60);
					((DiviContext) _localctx).out = match(REGISTER);
					setState(61);
					((DiviContext) _localctx).in = match(REGISTER);
					setState(62);
					((DiviContext) _localctx).in2 = match(NUMBER);
				}
					break;
				case T__16:
					_localctx = new AndContext(_localctx);
					enterOuterAlt(_localctx, 15); {
					setState(63);
					match(T__16);
					setState(64);
					((AndContext) _localctx).out = match(REGISTER);
					setState(65);
					((AndContext) _localctx).in = match(REGISTER);
					setState(66);
					((AndContext) _localctx).in2 = match(REGISTER);
				}
					break;
				case T__17:
					_localctx = new OrContext(_localctx);
					enterOuterAlt(_localctx, 16); {
					setState(67);
					match(T__17);
					setState(68);
					((OrContext) _localctx).out = match(REGISTER);
					setState(69);
					((OrContext) _localctx).in = match(REGISTER);
					setState(70);
					((OrContext) _localctx).in2 = match(REGISTER);
				}
					break;
				case T__18:
					_localctx = new NotContext(_localctx);
					enterOuterAlt(_localctx, 17); {
					setState(71);
					match(T__18);
					setState(72);
					((NotContext) _localctx).out = match(REGISTER);
					setState(73);
					((NotContext) _localctx).in = match(REGISTER);
				}
					break;
				case T__19:
					_localctx = new BeqContext(_localctx);
					enterOuterAlt(_localctx, 18); {
					setState(74);
					match(T__19);
					setState(75);
					((BeqContext) _localctx).in = match(REGISTER);
					setState(76);
					((BeqContext) _localctx).in2 = match(REGISTER);
					setState(77);
					match(LABEL);
				}
					break;
				case T__20:
					_localctx = new BleqContext(_localctx);
					enterOuterAlt(_localctx, 19); {
					setState(78);
					match(T__20);
					setState(79);
					((BleqContext) _localctx).in = match(REGISTER);
					setState(80);
					((BleqContext) _localctx).in2 = match(REGISTER);
					setState(81);
					match(LABEL);
				}
					break;
				case T__21:
					_localctx = new BContext(_localctx);
					enterOuterAlt(_localctx, 20); {
					setState(82);
					match(T__21);
					setState(83);
					match(LABEL);
				}
					break;
				case LABEL:
					_localctx = new LabelContext(_localctx);
					enterOuterAlt(_localctx, 21); {
					setState(84);
					match(LABEL);
					setState(85);
					match(T__22);
				}
					break;
				case T__23:
					_localctx = new JalContext(_localctx);
					enterOuterAlt(_localctx, 22); {
					setState(86);
					match(T__23);
					setState(87);
					match(LABEL);
				}
					break;
				case T__24:
					_localctx = new JrContext(_localctx);
					enterOuterAlt(_localctx, 23); {
					setState(88);
					match(T__24);
					setState(89);
					match(REGISTER);
				}
					break;
				case T__25:
					_localctx = new DelContext(_localctx);
					enterOuterAlt(_localctx, 24); {
					setState(90);
					match(T__25);
					setState(91);
					match(REGISTER);
				}
					break;
				case T__26:
					_localctx = new PrintContext(_localctx);
					enterOuterAlt(_localctx, 25); {
					setState(92);
					match(T__26);
					setState(93);
					match(REGISTER);
				}
					break;
				case T__27:
					_localctx = new HaltContext(_localctx);
					enterOuterAlt(_localctx, 26); {
					setState(94);
					match(T__27);
				}
					break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$d\4\2\t\2\4\3\t\3"
			+
			"\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
			"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
			"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
			"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
			"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
			"\3\3\3\3\3\3\3\3\3\3\3\5\3b\n\3\3\3\2\2\4\2\4\2\2\2{\2\t\3\2\2\2\4a\3" +
			"\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n" +
			"\3\3\2\2\2\13\t\3\2\2\2\f\r\7\3\2\2\rb\7!\2\2\16b\7\4\2\2\17\20\7\5\2" +
			"\2\20\21\7!\2\2\21\22\7 \2\2\22\23\7\6\2\2\23\24\7!\2\2\24b\7\7\2\2\25" +
			"\26\7\b\2\2\26\27\7!\2\2\27\30\7 \2\2\30\31\7\6\2\2\31\32\7!\2\2\32b\7" +
			"\7\2\2\33\34\7\t\2\2\34\35\7!\2\2\35b\7 \2\2\36\37\7\n\2\2\37 \7!\2\2" +
			" b\7!\2\2!\"\7\13\2\2\"#\7!\2\2#$\7!\2\2$b\7!\2\2%&\7\f\2\2&\'\7!\2\2" +
			"\'(\7!\2\2(b\7!\2\2)*\7\r\2\2*+\7!\2\2+,\7!\2\2,b\7!\2\2-.\7\16\2\2./" +
			"\7!\2\2/\60\7!\2\2\60b\7!\2\2\61\62\7\17\2\2\62\63\7!\2\2\63\64\7!\2\2" +
			"\64b\7 \2\2\65\66\7\20\2\2\66\67\7!\2\2\678\7!\2\28b\7 \2\29:\7\21\2\2" +
			":;\7!\2\2;<\7!\2\2<b\7 \2\2=>\7\22\2\2>?\7!\2\2?@\7!\2\2@b\7 \2\2AB\7" +
			"\23\2\2BC\7!\2\2CD\7!\2\2Db\7!\2\2EF\7\24\2\2FG\7!\2\2GH\7!\2\2Hb\7!\2" +
			"\2IJ\7\25\2\2JK\7!\2\2Kb\7!\2\2LM\7\26\2\2MN\7!\2\2NO\7!\2\2Ob\7\37\2" +
			"\2PQ\7\27\2\2QR\7!\2\2RS\7!\2\2Sb\7\37\2\2TU\7\30\2\2Ub\7\37\2\2VW\7\37" +
			"\2\2Wb\7\31\2\2XY\7\32\2\2Yb\7\37\2\2Z[\7\33\2\2[b\7!\2\2\\]\7\34\2\2" +
			"]b\7!\2\2^_\7\35\2\2_b\7!\2\2`b\7\36\2\2a\f\3\2\2\2a\16\3\2\2\2a\17\3" +
			"\2\2\2a\25\3\2\2\2a\33\3\2\2\2a\36\3\2\2\2a!\3\2\2\2a%\3\2\2\2a)\3\2\2" +
			"\2a-\3\2\2\2a\61\3\2\2\2a\65\3\2\2\2a9\3\2\2\2a=\3\2\2\2aA\3\2\2\2aE\3" +
			"\2\2\2aI\3\2\2\2aL\3\2\2\2aP\3\2\2\2aT\3\2\2\2aV\3\2\2\2aX\3\2\2\2aZ\3" +
			"\2\2\2a\\\3\2\2\2a^\3\2\2\2a`\3\2\2\2b\5\3\2\2\2\4\ta";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}