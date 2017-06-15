package org.forkjoin.apikit.example.client.model;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * 用户信息
 */
public class User implements ApiMessage {

	private long id;

	/**
	 * 名称允许重复
	 */
	private String name;

	/**
	 * 年龄
	 */
	private int age;

	public User() {
	}

	public User(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 名称允许重复
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称允许重复
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 年龄
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 年龄
	 */
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public List<Entry<String, Object>> encode(String $parent, List<Entry<String, Object>> $list) {

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "id", id));

		if (name != null) {
			$list.add(new SimpleImmutableEntry<String, Object>($parent + "name", name));
		}

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "age", age));

		return $list;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ",name=" + name + ",age=" + age + ", ]";
	}
}