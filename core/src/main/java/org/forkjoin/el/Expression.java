package org.forkjoin.el;

public interface Expression<T> {
	T el(Object obj);
}
