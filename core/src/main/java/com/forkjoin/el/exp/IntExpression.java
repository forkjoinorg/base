package com.forkjoin.el.exp;

import com.forkjoin.el.Expression;

public abstract class IntExpression implements Expression<Integer> {
	public Integer el(Object obj){
		return intEl(obj);
	}
	public abstract int intEl(Object obj);
}
