package com.unibo.ci.util;

public class EffectHelper {
	
	static public enum ETypes {
			BOT, RW, D, T 
	}
	
	public EffectHelper(){
		
	}
	
	public static ETypes seq(ETypes a, ETypes b) {
		if (max(a, b).ordinal() <= ETypes.RW.ordinal()) {
			return max(a, b);
		}
		if ((a.ordinal() <= ETypes.RW.ordinal() && b == ETypes.D) || (a == ETypes.D && b == ETypes.BOT)) {
			return ETypes.D;
		}
		
		return ETypes.T; 
	}
	
	public static ETypes max(ETypes a, ETypes b) {
		return a.ordinal() > b.ordinal() ? a : b ;		
	}
	
	public static ETypes par(ETypes a, ETypes b) {
		if ( a == ETypes.BOT || b == ETypes.BOT) {
			return ETypes.BOT;
		}
		if ( a == b && a == ETypes.RW) {
			return ETypes.RW;
		}
		return ETypes.T;
		
	}
	
	
}
