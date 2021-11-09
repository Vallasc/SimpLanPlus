package com.unibo.ci.util;

import com.unibo.ci.ast.types.Type;

public class STentry extends Entry<Type> {

    public STentry(String id, Type type, int nestLevel, int offset) {
    	super(id, type, nestLevel, offset);
    }


    public String toPrint(String indent) {
        return indent + "STentry:\n" + indent + "\tNesting Level: " + nestLevel + "\n" + indent + "\tId: \"" + this.id
                + "\"\n" + type.toPrint(indent + "\t") + indent + "\tOffset: \"" + this.offset + "\"\n";
    }

}