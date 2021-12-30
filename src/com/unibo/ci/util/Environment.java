package com.unibo.ci.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public abstract class Environment<T> implements Cloneable {

	LinkedList<LinkedHashMap<String, T>> table;
	int nestingLevel;
	
	public Environment() {
		this.table = new LinkedList<LinkedHashMap<String, T>>();
	}

	// Extends the symbol table with a new scope
	public void newScope() {
		nestingLevel++;
		table.add(new LinkedHashMap<String, T>());
	}

	// Remove the last scope
	public void exitScope() {
		nestingLevel--;
		table.removeLast();
	}

	public int getNestingLevel() {
		return nestingLevel;
	}

	// If there is no clash of names, adds id ⟼ t to st
	// T può essere STEntry o un effetto
	/*
	 * public abstract void addDeclaration (String id, <? extends Entry> type)
	 * throws DuplicateEntryException ; /* { // public void addDeclaration(String
	 * id, Type type) throws DuplicateSTEntryException { T value =
	 * table.getLast().get(id); // There is already an entry if (value != null)
	 * throw new DuplicateEntryException(); symTable.getLast().put(id, new ); }
	 */

	// Looks for the entry of id in symbol/effect table, if there is any
	public T lookup(String id) {
		for (int i = table.size(); i-- > 0;) {
			T entry = table.get(i).get(id);
			if (entry != null) {

				return entry; // .getType();
			}
		}
		return null;
	}

	public LinkedList<LinkedHashMap<String, T>> getTable() {
		return this.table;
	};

	public abstract String toPrint(String indent);

	/*
	 * public void setSymTable(LinkedList<HashMap<String, STentry>> symTable) {
	 * this.symTable = symTable; }
	 * 
	 * public int getNestingLevel() { return nestingLevel; }
	 * 
	 * public void setNestingLevel(int nestingLevel) { this.nestingLevel =
	 * nestingLevel; }
	 * 
	 * public int getOffset() { return offset; }
	 * 
	 * public void setOffset(int offset) { this.offset = offset; }
	 */

	public static class DuplicateEntryException extends Exception {

	}

}
