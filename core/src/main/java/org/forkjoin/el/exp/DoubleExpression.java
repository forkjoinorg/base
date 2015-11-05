package org.forkjoin.el.exp;

import org.forkjoin.el.Expression;

public abstract class DoubleExpression implements Expression<Double> {
	public Double el(Object obj){
		return doubleEl(obj);
	}
	public abstract double doubleEl(Object obj);
}
