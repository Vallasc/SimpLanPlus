package com.unibo.ci.svm;

public class Instruction {
	private final String instr;
	private final String arg1;
	private final int offset;
	private final String arg2;
	private final String arg3;

	public Instruction(String instr, String arg1, int offset, String arg2, String arg3) {
		this.instr = instr;
		this.arg1 = arg1;
		this.offset = offset;
		this.arg2 = arg2;
		this.arg3 = arg3;
	}

	public String getInstruction() {
		return instr;
	}

	public String getArg1() {
		return arg1;
	}

	public int getOffset() {
		return offset;
	}

	public String getArg2() {
		return arg2;
	}

	public String getArg3() {
		return arg3;
	}

	public void printInstruction() {
		System.out.println(instr + " " + arg1 + " " + offset + "(" + arg2 + ")" + " " + arg3);
	}

}
