package com.forkjoin.el.exp;

import com.forkjoin.el.Expression;

public abstract class ByteExpression implements Expression<Byte> {
	public Byte el(Object obj){
		return byteEl(obj);
	}
	public abstract byte byteEl(Object obj);
}
