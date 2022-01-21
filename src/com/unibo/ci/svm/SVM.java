package com.unibo.ci.svm;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.unibo.ci.util.GlobalConfig;

public class SVM {
	private final static Logger LOGGER = Logger.getLogger(SVM.class.getCanonicalName());
	private final int memSize; // heap + stack

	private final List<Instruction> code;
	private final MemoryCell[] memory;

	private int ip = 0;
	private HashMap<String, Integer> registers;

	public SVM(int memSize, List<Instruction> code) {
		this.memSize = memSize;
		this.code = code;

		memory = new MemoryCell[memSize];
		for (int i = 0; i < memSize; i++) {
			memory[i] = new MemoryCell();
		}

		registers = new HashMap<>();
		registers.put("$sp", memSize);
		registers.put("$cl", memSize);
		registers.put("$fp", memSize - 1);
		registers.put("$hp", 0);
		registers.put("$ra", null);
		registers.put("$al", null);
		registers.put("$a0", null);
		registers.put("$t1", null);

	}

	private int getFirstHeapMemoryCell() {
		int i = 0;
		while (!memory[i].isFree())
			i++;
		return i;
	}

	public void run() throws MemoryAccessException {
		while (true) {
			if (registers.get("$hp") + 1 >= registers.get("$sp")) {
				if(GlobalConfig.SHOW_MEM){
					LOGGER.severe("Accessing a not initialized memory cell");
					printMemory();
				}
				throw new MemoryAccessException();
			} else {
				Instruction bytecode = code.get(ip);
				ip++;
				String arg1 = bytecode.getArg1();
				String arg2 = bytecode.getArg2();
				String arg3 = bytecode.getArg3();
				int offset = bytecode.getOffset();
				switch (bytecode.getInstruction()) {
					case "push":
						registers.put("$sp", registers.get("$sp") - 1);
						memory[registers.get("$sp")].setData(registers.get(arg1));
						break;
					case "pop":
						registers.put("$sp", registers.get("$sp") + 1);

						break;
					case "lw":
						// pop the value x on top of the stack and push MEMORY[x]
						int address;
						try {
							address = memory[registers.get(arg2) + offset].getData();

						} catch (IndexOutOfBoundsException | NotInitializedVariableException e) {
							if(GlobalConfig.SHOW_MEM){
								System.err.println("Instruction: " + bytecode.toString());
								printMemory();
							}
							throw new MemoryAccessException();
						}
						registers.put(arg1, address); // lw $r1 offset($r2)
						break;
					case "sw":

						if (arg2.equals("$hp")) {
							int heapMemCell = getFirstHeapMemoryCell();
							memory[heapMemCell].setData(registers.get(arg1));
							registers.put("$a0", heapMemCell); // //after sw automatically save the value saved in $a0,
																// it's essentially needed for pointer initialization
						} else
							memory[registers.get(arg2) + offset].setData(registers.get(arg1));
						break;
					case "li":
						registers.put(arg1, Integer.parseInt(arg2));
						break;
					case "mv":
						registers.put(arg1, registers.get(arg2));
						break;
					case "add":
						registers.put(arg1, registers.get(arg2) + registers.get(arg3));
						break;
					case "sub":
						registers.put(arg1, registers.get(arg2) - registers.get(arg3));
						break;
					case "mul":
						registers.put(arg1, registers.get(arg2) * registers.get(arg3));
						break;
					case "div":
						registers.put(arg1, registers.get(arg2) / registers.get(arg3));
						break;
					case "addi":
						registers.put(arg1, registers.get(arg2) + Integer.parseInt(arg3));
						break;
					case "subi":
						registers.put(arg1, registers.get(arg2) - Integer.parseInt(arg3));
						break;
					case "muli":
						registers.put(arg1, registers.get(arg2) * Integer.parseInt(arg3));
						break;
					case "divi":
						registers.put(arg1, registers.get(arg2) / Integer.parseInt(arg3));
						break;
					case "and":
						if (registers.get(arg2) == 1 && registers.get(arg3) == 1)
							registers.put(arg1, 1);
						else
							registers.put(arg1, null);
						break;
					case "or":
						if (registers.get(arg2) == 1 || registers.get(arg3) == 1)
							registers.put(arg1, 1);
						else
							registers.put(arg1, null);
						break;
					case "not":
						registers.put(arg1, registers.get(arg2) == 1 ? 0 : 1);
						break;
					case "del":
						memory[registers.get(arg1)].freeCell();
						break;
					case "print":
						System.out.println(registers.get(arg1));
						break;
					case "printchar":
						System.out.println(Character.toChars(registers.get(arg1).intValue()));
						break;
					case "beq":
						if (registers.get(arg1) == registers.get(arg2))
							ip = Integer.parseInt(arg3);
						break;
					case "bleq":
						if (registers.get(arg1) <= registers.get(arg2))
							ip = Integer.parseInt(arg3);
						break;
					case "b":
						ip = Integer.parseInt(arg1);
						break;
					case "jal":
						registers.put("$ra", ip);
						ip = Integer.parseInt(arg1);
						break;
					case "jr":
						ip = registers.get(arg1);
						break;
					case "halt":
						if(GlobalConfig.SHOW_MEM && !(GlobalConfig.SHOW_DEBUG)){
							System.err.println("\nInstruction: " + bytecode.toString());
							printMemory();
						}
						return;
					default:
						System.err.println("Unrecognized instruction: " + bytecode.getInstruction());
						return;
				}
				if(GlobalConfig.SHOW_DEBUG){
					System.err.println("\nInstruction: " + bytecode.toString());
					printMemory();
				}
			}
		}
	}

	void printMemory(){
		System.out.println("  -----------------------");
		System.out.println("  | Registers\t| Value\t|");
		System.out.println("  -----------------------");
		System.err.println("  | IP \t\t| " + ip + "\t| ");
		System.err.println("  | SP \t\t| " + registers.get("$sp") + "\t| ");
		System.err.println("  | CL \t\t| " + registers.get("$cl") + "\t| ");
		System.err.println("  | FP \t\t| " + registers.get("$fp") + "\t| ");
		System.err.println("  | RA \t\t| " + registers.get("$ra") + "\t| ");
		System.err.println("  | AL \t\t| " + registers.get("$al") + "\t| ");
		System.err.println("  | A0 \t\t| " + registers.get("$a0") + "\t| ");
		System.err.println("  | T1 \t\t| " + registers.get("$t1") + "\t| ");
		System.err.println("  | HP \t\t| " + registers.get("$hp") + "\t| ");
		System.out.println("  -----------------------");
		System.err.println("  | Address\t| Value\t| ");
		System.out.println("  -----------------------");
		for(int i =0 ; i< memSize; i++ )
			System.out.println("  | " + i + "\t\t| "+ memory[i].toString() + "\t|");
		System.out.println("  -----------------------");
	}

	public class MemoryAccessException extends Exception {
	}

}

class MemoryCell {
	private Integer data;
	private boolean isUsed;

	public MemoryCell(int data, boolean isUsed) {
		this.data = data;
		this.isUsed = isUsed;
	}

	public MemoryCell() {
		data = null;
		isUsed = false;
	}

	public int getData() throws NotInitializedVariableException {
		if (data == null) {
			throw new NotInitializedVariableException();
		} else {
			return data;
		}
	}

	public void setData(Integer data) {
		this.data = data;
		this.isUsed = true;
	}

	public void freeCell() {
		isUsed = false;
	}

	public boolean isFree() {
		return !isUsed;
	}

	@Override
	public String toString() {
		if(data != null)
			return data.toString();
		else 
			return "null";
	}

}

class NotInitializedVariableException extends Exception {}