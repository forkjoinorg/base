package org.forkjoin.jdbckit.core;


import org.forkjoin.jdbckit.core.identifier.Field;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<Item> items = new ArrayList<>();
	
	private Order(){
		
	}
	
	public  static Order create(){
		return new Order();
	}

	public  static Order create(Field name, boolean isDesc){
		Order p= new Order();
		p.add(name, isDesc);
		return p;
	}

	public static Order desc(Field name){
		return createSingleton(name, true);
	}

	public static Order asc(Field name){
		return createSingleton(name, false);
	}

	public  static Order createSingleton(final Field name, final boolean isDesc){
		return new Order(){
			@Override
			public void toSql(StringBuilder sb) {
				sb.append(" ORDER BY ");
				sb.append(name);
				if (isDesc) {
					sb.append(" DESC");
				}else{
					sb.append(" ASC");
				}
			}
			@Override
			public void add(Field name, boolean isDesc) {
				throw new  IllegalStateException("单例Order不能添加");
			}
		};
	}

	/**
	 * expression
     */
	public static Order createByExp(final String expression, final boolean isDesc){
		return new Order(){
			@Override
			public void toSql(StringBuilder sb) {
				sb.append(" ORDER BY ");
				sb.append(expression);
				if (isDesc) {
					sb.append(" DESC");
				}else{
					sb.append(" ASC");
				}
			}
			@Override
			public void add(Field name, boolean isDesc) {
				throw new  IllegalStateException("单例Order不能添加");
			}
		};
	}

	public void add(Field name, boolean isDesc) {
		items.add(new Item(name, isDesc));
	}

	public String toSql() {
		StringBuilder sb=new StringBuilder();
		toSql(sb);
		return sb.toString();
	}

	public void toSql(StringBuilder sb) {
		if(items.isEmpty()){
			return ;
		}
		sb.append(" ORDER BY ");
		boolean fist=true;
		for(Item item:items){
			if(fist){
				fist=false;
			}else{
				sb.append(',');
			}
			sb.append(item.name);
			if (item.desc) {
				sb.append(" DESC");
			}else{
				sb.append(" ASC");
			}
		}
	}

	static class Item {
		Item(Field name, boolean desc) {
			this.name = name;
			this.desc = desc;
		}

		Field name;
		boolean desc;
	}
}
