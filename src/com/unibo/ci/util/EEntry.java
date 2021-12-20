package com.unibo.ci.util;

import java.util.List;

import com.unibo.ci.util.EffectHelper.ETypes;

public class EEntry extends Entry<ETypes> {

  private List<EEntry> funEffects;
  
  SigmaEnv sigma0;
  SigmaEnv sigma1; 

  public EEntry(String id, ETypes type, int nestLevel, int offset) {
    super(id, type, nestLevel, offset);
  }

  public String toPrint(String indent) {
    return indent + "EEentry:\n" + indent + "\tNesting Level: " + nestLevel + "\n" + indent + "\tId: \"" + this.id
        + "\"\n" + indent + "Effect: " + type + "\n" + indent + "\tOffset: \"" + this.offset + "\"\n";
  };

  public ETypes getEtype() {
    return super.type;
  }

  public void updateEffectType(ETypes type) {
    this.type = type;
  }

  @Override
  public boolean isNotFunction() {
	  return sigma0 != null;
    //return funEffects != null;
  }

  public SigmaEnv getSigma0() {
	return this.sigma0;
  }
  
  public SigmaEnv getSigma1() {
	return this.sigma1;
  }

}
