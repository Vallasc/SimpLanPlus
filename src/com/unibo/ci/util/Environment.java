package com.unibo.ci.util;

import java.util.HashMap;
import java.util.LinkedList;

import com.unibo.ci.ast.types.Type;
import com.unibo.ci.ast.types.TypeInt;

public class Environment {
	
	//THESE VARIABLES SHOULDN'T BE PUBLIC
	//THIS CAN BE DONE MUCH MUCH MUCH MUCH MUCH MUCH MUHC 
	// MUCH MUCH MUCH MUCH MUCH MUCH MUCH MUCH MUCH MCUHCUHCU N d BETTER
	
	private LinkedList<HashMap<String,STentry>>  symTable = new LinkedList<HashMap<String,STentry>>();
	private int nestingLevel = -1;
	private int offset = 0; //TODO VEDERE COME FUNZIONA OFFSET

	// Extends the symbol table with a new scope
	public void newScope(){
		nestingLevel++;
		symTable.add(new HashMap<String,STentry>());
	}

	// Remove the last scope
	public void exitScope(){
		nestingLevel--;
		symTable.removeLast();
	}

	// If there is no clash of names, adds id ⟼ t to st 
	public void addDeclaration(String id, Type t) throws Exception {
		STentry value = symTable.getLast().get(id);
		// There is already an entry
		if(value != null)
			throw new Exception();
		symTable.getLast().put(id, new STentry(nestingLevel, offset++, t));
	}

	// Looks for the type of id in symbol table, if there is any 
	public Type lookup(String id) {	
		for (int i = symTable.size(); i-- > 0; ) {
			STentry entry = symTable.get(i).get(id);
			if( entry != null){
				return entry.getType();
			}
		}

		return null;
	}


	//livello ambiente con dichiarazioni piu' esterno � 0 (prima posizione ArrayList) invece che 1 (slides)
	//il "fronte" della lista di tabelle � symTable.get(nestingLevel)
	
	// @Override
	// public boolean add(String id, T entry) {
	// 	T prev = getLocalIDEntry(id);
	// 	if (prev != null && !prev.isDeleted())
	// 		return false;

	// 	entry.setDeleted(false);

	// 	if (entry instanceof STentry) {
	// 		STentry stentry = (STentry) entry;
	// 		if (prev == null) {
	// 			stentry.offset = entry.getOffset();
	// 			this.offset += stentry.getType().getDimension();
	// 			stentry.nestingLevel = getNestingLevel();
	// 		}
	// 	}

	// 	scopes.peek().put(id, entry);
	// 	return true;
	// }

	// /**
	//  * Insert a new scope into the environment
	//  */
	
	// public void openScope() {
	// 	scopes.push(new HashMap<String, T>());
	// }

	// /**
	//  * Drop the current scope
	//  */
	// public void closeScope() {
	// 	scopes.pop();
	// }

	// public T deleteVariable(String id) {
	// 	T toRet = getIDEntry(id);
	// 	if (toRet != null)
	// 		toRet.setDeleted(true);
	// 	return toRet;
	// }

	// public T getIDEntry(String id) {
	// 	for (HashMap<String, T> scope : scopes)
	// 		if (scope.containsKey(id) && !scope.get(id).isDeleted())
	// 			return scope.get(id);
	// 	return null;
	// }

	// public T getLocalIDEntry(String id) {
	// 	return scopes.peek().get(id);
	// }

	// public int getNestingLevel() {
	// 	return nestingLevel;
	// }

	// public T update(String id, T entry) {
	// 	for (HashMap<String, T> scope : scopes)
	// 		if (scope.containsKey(id) && !scope.get(id).isDeleted())
	// 			return scope.put(id, entry);
	// 	return null;
	// }

	// public Map<String, T> getAllIDs() {
	// 	Map<String, T> toRet = new HashMap<String, T>();

	// 	/**
	// 	 * Using the descending iterator, it is possible to replace existing ids values
	// 	 * with a more specific one derived by the inner scope
	// 	 */
	// 	scopes.descendingIterator().forEachRemaining(scope -> toRet.putAll(scope));

	// 	return toRet;
	// }

	// public LinkedList<HashMap<String, T>> getAllFunctions() {
	// 	LinkedList<HashMap<String, T>> toRet = new LinkedList<HashMap<String, T>>();
	// 	scopes.descendingIterator().forEachRemaining(s -> {
	// 		HashMap<String, T> toAdd = new HashMap<String, T>();

	// 		/**
	// 		 * <code>e.getValue() != null && (e.getValue().isFunction()</code> checks that
	// 		 * the current entry is a function
	// 		 */
	// 		toAdd.putAll(s.entrySet().stream().filter(e -> e.getValue() != null && (e.getValue().isFunction()))
	// 				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue())));
	// 		toRet.push(toAdd);
	// 	});
	// 	return toRet;
	// }

	// public int getOffset() {
	// 	return this.offset;
	// }

	// public void setOffset(int offset) {
	// 	this.offset = offset;
	// }

	// public void increaseNestingLevel() {
	// 	nestingLevel++;
	// }

	// public void decreaseNestingLevel() {
	// 	nestingLevel--;
	// }

	// @SuppressWarnings("unchecked")
	// public boolean equals(Object obj) {
	// 	if (obj == null)
	// 		return false;
	// 	if (this == obj)
	// 		return true;

	// 	if (!obj.getClass().isInstance(this))
	// 		return false;
	// 	ListOfMapEnv<T> casted = (ListOfMapEnv<T>) obj;

	// 	if (casted.scopes.size() != scopes.size())
	// 		return false;

	// 	for (int i = 0; i < scopes.size(); i++) {
	// 		HashMap<String, T> thisScope = scopes.get(i);
	// 		HashMap<String, T> objScope = casted.scopes.get(i);

	// 		if (thisScope.size() != objScope.size())
	// 			return false;

	// 		Iterator<java.util.Map.Entry<String, T>> iterator = thisScope.entrySet().iterator();
	// 		while (iterator.hasNext()) {
	// 			java.util.Map.Entry<String, T> entry = iterator.next();
	// 			if (!objScope.containsKey(entry.getKey()) || !objScope.get(entry.getKey()).equals(entry.getValue()))
	// 				return false;
	// 		}
	// 	}

	// 	return true;
	// }

	// @SuppressWarnings("unchecked")
	// @Override
	// public Object clone() {
	// 	ListOfMapEnv<T> clonedEnv = null;

	// 	try {
	// 		clonedEnv = (ListOfMapEnv<T>) super.clone();
	// 		LinkedList<HashMap<String, T>> clonedScopes = new LinkedList<HashMap<String, T>>();

	// 		scopes.descendingIterator().forEachRemaining(scope -> {
	// 			clonedScopes.push(new HashMap<String, T>());
	// 			scope.forEach((k, v) -> clonedScopes.peek().put(k, (T) v.clone()));
	// 		});

	// 		clonedEnv.scopes = clonedScopes;
	// 	} catch (CloneNotSupportedException e) {
	// 		e.printStackTrace();
	// 	}

	// 	return clonedEnv;
	// }
	
}
