package com.unibo.ci.util;

import com.unibo.ci.util.EffectHelper.ETypes;

public class EEntry extends Entry<ETypes> {
   
	
	public EEntry(String id, ETypes type, int nestLevel, int offset) {
		super(id, type, nestLevel, offset);
    }
	
    public String toPrint(String indent) {
    	return indent + "EEentry:\n" + indent + "\tNesting Level: " + nestLevel + "\n" + indent + "\tId: \"" + this.id
                + "\"\n" + indent + "Effect: " + type + "\n" + indent + "\tOffset: \"" + this.offset + "\"\n";
    };

}
