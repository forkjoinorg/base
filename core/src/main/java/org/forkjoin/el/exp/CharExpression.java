package org.forkjoin.el.exp;

import org.forkjoin.el.Expression;

public abstract class CharExpression implements Expression<Character> {
	public Character el(Object obj){
		return charEl(obj);
	}
	public abstract char charEl(Object obj);
}
