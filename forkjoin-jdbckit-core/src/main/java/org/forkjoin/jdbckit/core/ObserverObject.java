package org.forkjoin.jdbckit.core;

/**
 * 观察者对象
 * @author zuoge85
 *
 */
public interface ObserverObject{
	boolean isChange();
	void onSaveAfter();
	void setObserver(Observer obs);
}
