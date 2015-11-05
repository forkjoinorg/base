package org.forkjoin.el.exp;

import org.forkjoin.el.Expression;

public abstract class FloatExpression implements Expression<Float> {
	public Float el(Object obj){
		return floatEl(obj);
	}
	public abstract float floatEl(Object obj);
}
