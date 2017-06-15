package org.forkjoin.apikit.example.client.collecton;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * api的list集合,用于封装list数据
 * @author  zuoge85 on 15/8/11.
 */
public class ApiList<T extends ApiMessage> implements ApiMessage {

	private java.util.ArrayList<T> list;

	public ApiList() {
	}

	public ApiList(java.util.ArrayList<T> list) {
		this.list = list;
	}

	public java.util.ArrayList<T> getList() {
		return list;
	}

	public void setList(java.util.ArrayList<T> list) {
		this.list = list;
	}

	public void addList(T list) {
		if (this.list == null) {
			this.list = new java.util.ArrayList<T>();
		}
		this.list.add(list);
	}

	@Override
	public List<Entry<String, Object>> encode(String $parent, List<Entry<String, Object>> $list) {

		if (list != null && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).encode($parent + "list" + "[" + i + "].", $list);
			}
		}

		return $list;
	}

	@Override
	public String toString() {
		return "ApiList [list=size:" + (list == null ? -1 : list.size()) + ", ]";
	}
}