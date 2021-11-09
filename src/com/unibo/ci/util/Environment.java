package com.unibo.ci.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;



public abstract class Environment <T> {

	LinkedList<LinkedHashMap<String, T>> table;
	int nestingLevel; 
	int offset; // TODO VEDERE COME FUNZIONA OFFSET

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

	// If there is no clash of names, adds id ⟼ t to st
	//T può essere STEntry o un effetto
	/*public abstract void addDeclaration (String id, <? extends Entry> type) throws DuplicateEntryException ; /* { 
		// public void addDeclaration(String id, Type type) throws DuplicateSTEntryException {
			T value = table.getLast().get(id);
			// There is already an entry
			if (value != null)
				throw new DuplicateEntryException();
			symTable.getLast().put(id, new );
		}*/

	// Looks for the entry of id in symbol/effect table, if there is any
	public T lookup(String id) {
		for (int i = table.size(); i-- > 0;) {
			T entry = table.get(i).get(id);
			if (entry != null) {
				
				return entry ; //.getType();
			}
		}
		return null;
	} 

	public T lookupFunction() {
			for (int i = table.size() - 2; i >= 0; i--) {
				ListIterator<T> iterator = new ArrayList<T>(table.get(i).values()).listIterator(table.get(i).size());

				while (iterator.hasPrevious()) {
					T entry = iterator.previous();
					// System.out.println("DEBUG Entry: " + entry.toPrint("*"));
					// if (entry.getType() instanceof TypeFunction)
					// return entry;
				}
			}
			return null;
	}
	
	public LinkedList<LinkedHashMap<String, T>> getTable(){
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
