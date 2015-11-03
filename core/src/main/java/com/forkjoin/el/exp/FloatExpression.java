package com.forkjoin.el.exp;

import com.forkjoin.el.Expression;

public abstract class FloatExpression implements Expression<Float> {
	public Float el(Object obj){
		return floatEl(obj);
	}
	public abstract float floatEl(Object obj);
}
