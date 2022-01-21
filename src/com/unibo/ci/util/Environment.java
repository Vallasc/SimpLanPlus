package com.unibo.ci.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public abstract class Environment<T> implements Cloneable {

	LinkedList<LinkedHashMap<String, T>> table;
	protected int nestingLevel;
	
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
	// Looks for the entry of id in symbol/effect table, if there is any
	public T lookup(String id) {
		for (int i = table.size(); i-- > 0;) {
			T entry = table.get(i).get(id);
			if (entry != null) {
				return entry;
			}
		}
		return null;
	}

	public LinkedList<LinkedHashMap<String, T>> getTable() {
		return this.table;
	};

	public abstract String toPrint(String indent);

	public static class DuplicateEntryException extends Exception {

	}

}
