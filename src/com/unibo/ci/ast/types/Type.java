package com.unibo.ci.ast.types;

public abstract class Type {

    protected final String TYPE_NAME;
    protected final int DIMENSION;

    public Type(String type_name, int dimension){
        this.TYPE_NAME = type_name;
        this.DIMENSION = dimension;
        
    }

    public int getDimension(){
        return DIMENSION;
    };
    public String getTypeName(){
        return TYPE_NAME;
    }
    public  abstract boolean equals(Object e);
}
