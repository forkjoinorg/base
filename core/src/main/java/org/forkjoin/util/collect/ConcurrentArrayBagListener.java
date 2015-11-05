package org.forkjoin.util.collect;

public interface ConcurrentArrayBagListener<E extends CoreGoods> {
	void onChanged();
	void onChangedItem(E e,int index);
}
