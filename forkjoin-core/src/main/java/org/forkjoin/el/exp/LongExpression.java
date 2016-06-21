package org.forkjoin.el.exp;

import org.forkjoin.el.Expression;

public abstract class LongExpression implements Expression<Long> {
	public Long el(Object obj){
		return longEl(obj);
	}
	public abstract long longEl(Object obj);
}
