package com.forkjoin.el.exp;

import com.forkjoin.el.Expression;

public abstract class DoubleExpression implements Expression<Double> {
	public Double el(Object obj){
		return doubleEl(obj);
	}
	public abstract double doubleEl(Object obj);
}
