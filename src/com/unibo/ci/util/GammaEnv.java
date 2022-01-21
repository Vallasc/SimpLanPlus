package com.unibo.ci.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeFunction;

public class GammaEnv extends Environment<STentry> {
	private int offset;

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
		offset = 0;
	}

	// Remove the last scope
	public void exitScope() {
		super.exitScope();
		if(this.nestingLevel >= 0) {
			int maxOffset = 0; //offset is a negative value
			HashMap<String, STentry> entry = table.get(nestingLevel);
			for(String key : entry.keySet()) {
				if(maxOffset > entry.get(key).getOffset())
					maxOffset = entry.get(key).getOffset();
			}
			offset = maxOffset;	
		}
	}

	// If there is no clash of names, adds id ⟼ t to st
	public void addDeclaration(String id, Type type) throws DuplicateEntryException {
		STentry value = table.getLast().get(id);
		// There is already an entry
		if (value != null)
			throw new DuplicateEntryException();
		table.getLast().put(id, new STentry(id, type, nestingLevel, offset));
		offset--; 
	}

	public void addDeclarationPar(String id, Type type) throws DuplicateEntryException {
		STentry value = table.getLast().get(id);
		// There is already an entry
		if (value != null){
			throw new DuplicateEntryException();
		}
		//STentry newEntry = new STentry(id, type, nestingLevel-1, offset);
		STentry newEntry = new STentry(id, type, nestingLevel, offset);
		newEntry.setIsPar(true);
		table.getLast().put(id, newEntry);
		offset--; 
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
				if (entry.getType() instanceof TypeFunction)
					return entry;
			}
		}
		return null;
	}

	public STentry lookupFunction(String id){
		for (int i = table.size(); i-- > 0;) {
			STentry entry = table.get(i).get(id);
			if (entry != null && entry.getType() instanceof TypeFunction) {

				return entry; // .getType();
			}
		}
		return null;
	}

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

}
