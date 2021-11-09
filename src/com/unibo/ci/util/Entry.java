package com.unibo.ci.util;

public abstract class Entry<T> {
    
	final int nestLevel;
    final T type;
    final String id;
    final int offset;
	
	public Entry(String id, T type, int nestLevel, int offset) {
        this.nestLevel = nestLevel;
        this.offset = offset;
        this.type = type;
        this.id = id;
    }

	public T getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public int getNestinglevel() {
        return nestLevel;
    }

    public String getId() {
        return id;
    }
	
    public String toPrint(String indent){
        return ""; //TODO
    };

}
