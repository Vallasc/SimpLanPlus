package com.unibo.ci.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.Map.Entry;

import com.unibo.ci.ast.stmt.ReturnStmt;
import com.unibo.ci.ast.stmt.block.BlockBase;
import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;

public class Environment {

	private LinkedList<LinkedHashMap<String, STentry>> symTable = new LinkedList<LinkedHashMap<String, STentry>>();
	private int nestingLevel = -1;
	private int offset = 0; // TODO VEDERE COME FUNZIONA OFFSET

	// Extends the symbol table with a new scope
	public void newScope() {
		nestingLevel++;
		symTable.add(new LinkedHashMap<String, STentry>());
	}

	// Remove the last scope
	public void exitScope() {
		nestingLevel--;
		symTable.removeLast();
	}

	// If there is no clash of names, adds id ⟼ t to st
	public void addDeclaration(String id, Type type) throws DuplicateSTEntryException {
		STentry value = symTable.getLast().get(id);
		// There is already an entry
		if (value != null)
			throw new DuplicateSTEntryException();
		symTable.getLast().put(id, new STentry(id, type, nestingLevel, offset++));
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

	public STentry lookupSTentry(String id) {
		for (int i = symTable.size(); i-- > 0;) {
			return symTable.get(i).get(id);
		}
		return null;
	}

	/*public STentry lookupFunction() {
		for (int i = symTable.size(); i-- > 0;) {
			ListIterator<STentry> iterator = new ArrayList<STentry>(symTable.get(i).values())
					.listIterator(symTable.get(i).size());
			while (iterator.hasPrevious()) {
				STentry entry = iterator.previous();
				if (entry.getType() instanceof TypeFunction)
					return entry;
			}
		}
		return null;
	}*/

	public LinkedList<LinkedHashMap<String, STentry>> getSymTable() {
		return symTable;
	}

	public String toPrint(String indent) {
		StringBuilder sb = new StringBuilder(indent + "Symbol table:\n");
		sb.append(indent + "---------------------------\n");
		symTable.forEach(scope -> {
			scope.forEach((id, entry) -> {
				sb.append(entry.toPrint(indent));
			});
			sb.append("---------------------------\n");
		});
		return sb.toString();
	}

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

	public class DuplicateSTEntryException extends Exception {
	}

}
