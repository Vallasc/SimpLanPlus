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
import com.unibo.ci.util.Environment;
import com.unibo.ci.util.STentry;

public class GammaEnv extends Environment<STentry> {

	public GammaEnv() {
		super();
		nestingLevel = -1;
		offset = 0;
	}

	public LinkedList<LinkedHashMap<String, STentry>> getTable() {
		return super.getTable();
	}

	// Extends the symbol table with a new scope
	public void newScope() {
		super.newScope();
	}

	// Remove the last scope
	public void exitScope() {
		super.exitScope();
	}

	// If there is no clash of names, adds id ⟼ t to st
	public void addDeclaration(String id, Type type) throws DuplicateEntryException {
		STentry value = table.getLast().get(id);
		// There is already an entry
		if (value != null)
			throw new DuplicateEntryException();
		table.getLast().put(id, new STentry(id, type, nestingLevel, offset));
	}

	// Looks for the entry of id in symbol/effect table, if there is any
	public STentry lookup(String id) {
		return super.lookup(id);
	}

	public STentry lookupFunction() {
		for (int i = table.size() - 2; i >= 0; i--) {
			ListIterator<STentry> iterator = new ArrayList<STentry>(table.get(i).values())
					.listIterator(table.get(i).size());

			while (iterator.hasPrevious()) {
				STentry entry = iterator.previous();
				// System.out.println("DEBUG Entry: " + entry.toPrint("*"));
				if (entry.getType() instanceof TypeFunction)
					return entry;
			}
		}
		return null;
	}

	/*
	 * public STentry lookupFunction() { for (int i = symTable.size(); i-- > 0;) {
	 * ListIterator<STentry> iterator = new
	 * ArrayList<STentry>(symTable.get(i).values())
	 * .listIterator(symTable.get(i).size()); while (iterator.hasPrevious()) {
	 * STentry entry = iterator.previous(); if (entry.getType() instanceof
	 * TypeFunction) return entry; } } return null; }
	 */

	public String toPrint(String indent) {
		StringBuilder sb = new StringBuilder(indent + "Symbol table:\n");
		sb.append(indent + "---------------------------\n");
		table.forEach(scope -> {
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

	/*
	 * public class DuplicateSTEntryException extends Exception { }
	 */

}
