package com.unibo.ci.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import com.unibo.ci.ast.types.TypeFunction;

public class SigmaEnv extends Environment<EEntry>{


    public SigmaEnv(){
    	super();
        nestingLevel = -1;
        offset = 0;
    }

    public LinkedList<LinkedHashMap<String, EEntry>> getTable(){
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
	public void addDeclaration(String id, EffectHelper.ETypes type) throws DuplicateEntryException { 
		EEntry value = table.getLast().get(id);
		// There is already an entry
		if (value != null)
			throw new DuplicateEntryException();
		table.getLast().put(id, new EEntry(id, type, nestingLevel, offset));
	}
	
	

	// Looks for the entry of id in symbol/effect table, if there is any
	public EEntry lookup(String id) {
		return super.lookup(id);
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

	
    @Override
    public String toPrint(String indent) {
        // TODO Auto-generated method stub
        return null;
    }

}