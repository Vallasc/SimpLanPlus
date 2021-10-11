package com.unibo.ci.util;

import java.util.HashMap;
import java.util.LinkedList;

import com.unibo.ci.ast.types.Type;

public class Environment {
	
	private LinkedList<HashMap<String,STentry>>  symTable = new LinkedList<HashMap<String,STentry>>();
	private int nestingLevel = -1;
	private int offset = 0; // TODO VEDERE COME FUNZIONA OFFSET

	// Extends the symbol table with a new scope
	public void newScope() {
		nestingLevel++;
		symTable.add(new HashMap<String, STentry>());
	}

	// Remove the last scope
	public void exitScope() {
		nestingLevel--;
		symTable.removeLast();
	}

	// If there is no clash of names, adds id ⟼ t to st
	public void addDeclaration(String id, Type t) throws DuplicateSTEntryException {
		STentry value = symTable.getLast().get(id);
		// There is already an entry
		if (value != null)
			throw new DuplicateSTEntryException();
		symTable.getLast().put(id, new STentry(nestingLevel, offset++, t));
	}

	// Looks for the type of id in symbol table, if there is any
	public Type lookup(String id) {
		for (int i = symTable.size(); i-- > 0;) {
			STentry entry = symTable.get(i).get(id);
			if (entry != null) {
				return entry.getType();
			}
		}

		return null;
	}

	public LinkedList<HashMap<String, STentry>> getSymTable() {
		return symTable;
	}

	public void setSymTable(LinkedList<HashMap<String, STentry>> symTable) {
		this.symTable = symTable;
	}

	public int getNestingLevel() {
		return nestingLevel;
	}

	public void setNestingLevel(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public class DuplicateSTEntryException extends Exception {
	}

}
