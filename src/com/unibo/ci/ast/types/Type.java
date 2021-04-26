package com.unibo.ci.ast.types;

public enum Type implements Cloneable {

    @Override
	public boolean equals(Object e) {
		if (e == null)
			return false;
		if (this == e)
			return true;
		if (!(e instanceof Type))
			return false;

		Type casted = (Type) e;
		if (casted.getType().compareTo(getType()) != 0)
			return false;

		return true;
	}

	@Override
	public Object clone() {
		return getType().getType(isParameter, isRef);
	}
}
