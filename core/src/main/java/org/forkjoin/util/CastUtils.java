package org.forkjoin.util;

public final class CastUtils {
	
	@SuppressWarnings("unchecked")
	public static <E> E as(Object o){
		return (E)o;
	}
}
