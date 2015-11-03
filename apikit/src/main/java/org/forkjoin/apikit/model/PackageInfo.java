package org.forkjoin.apikit.model;

import java.util.*;

/**
 * 包信息
 * @author zuoge85
 *
 */
public class PackageInfo<T extends ModuleInfo> {
	private Set<T> sets = new TreeSet<>(new Comparator<T>() {
		@Override
		public int compare(T o1, T o2) {
			return o1.getName().compareTo(o2.getName());
		}
	});
	private Map<String, T> map = new HashMap<>();
	
	private String name;
	private int type;
	
	
	public void add(T msg){
		msg.setPack(this);
		sets.add(msg);
		map.put(msg.getName(), msg);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getConstantName(int left){
		String str = name.toUpperCase();
		return String.format("%-" +left + "s", str.replace('.', '_'));
	}
	
	public Set<T> getValues() {
		return sets;
	}
	
	public T get(String name){
		return map.get(name);
	}

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}
}
