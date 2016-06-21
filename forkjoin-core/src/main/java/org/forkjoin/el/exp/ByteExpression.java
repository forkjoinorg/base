package org.forkjoin.el.exp;

import org.forkjoin.el.Expression;

public abstract class ByteExpression implements Expression<Byte> {
	public Byte el(Object obj){
		return byteEl(obj);
	}
	public abstract byte byteEl(Object obj);
}
