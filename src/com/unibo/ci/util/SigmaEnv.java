package com.unibo.ci.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


public class SigmaEnv extends Environment<EEntry> {

	public SigmaEnv() {
		super();
		nestingLevel = -1;
	}

	public LinkedList<LinkedHashMap<String, EEntry>> getTable() {
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
	public void addDeclaration(String id, EffectHelper.ETypes type) {
		table.getLast().put(id, new EEntry(id, type, nestingLevel));
	}

	public void addDeclaration(String id, SigmaEnv env0, SigmaEnv env1) { // per le funzioni
		table.getLast().put(id, new EEntry(id, null, -1));
		table.getLast().get(id).sigma0 = env0;
		table.getLast().get(id).sigma1 = env1;
	}

	// Looks for the entry of id in symbol/effect table, if there is any
	public EEntry lookup(String id) {
		for (int i = table.size(); i-- > 0;) {
			EEntry entry = table.get(i).get(id);
			
			if (entry != null) {
				
				return entry; // .getType();
			}
		}
		return null;
	}

	@Override
	public String toPrint(String indent) {

		StringBuilder sb = new StringBuilder();
		sb.append("\n== env: \n");

		table.forEach(env -> {

			env.keySet().forEach(key -> {
				sb.append("===== ").append(key).append(" |- ").append(env.get(key).type).append("\n");
			});

			sb.append("================== \n");
		}

		);
		sb.append("end env \n");

		return sb.toString();
	}

	@Override
	public SigmaEnv clone() {
		SigmaEnv toReturn = new SigmaEnv();
		toReturn.table = (LinkedList<LinkedHashMap<String, EEntry>>) table.clone();
		toReturn.nestingLevel = nestingLevel;
		return toReturn;
	}

	public Map<String, EEntry> getAllIDs() {

		Map<String, EEntry> map = new HashMap<String, EEntry>();
		table.descendingIterator().forEachRemaining(scope -> map.putAll(scope));
		return map;
	}

}