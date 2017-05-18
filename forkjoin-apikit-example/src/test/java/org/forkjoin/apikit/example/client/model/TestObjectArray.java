package org.forkjoin.apikit.example.client.model;

import org.forkjoin.apikit.spring.BytesConverter;
import java.util.Date;
import java.util.List;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @author  zuoge85 on 15/6/17.
 */
public class TestObjectArray<T extends ApiMessage> implements ApiMessage {

	private String id;

	/**
	 * 签名
	 * @see java.lang
	 * java.lang
	 */
	private boolean booleanValue;

	private int intValue;

	private long longValue;

	private float floatValue;

	private double doubleValue;

	private String stringValue;

	private List<Byte> bytesValue;

	private Date regDate;

	private List<Boolean> booleanValueArray;

	private List<Integer> intValueArray;

	private List<Long> longValueArray;

	private List<Float> floatValueArray;

	private List<Double> doubleValueArray;

	private List<String> stringValueArray;

	private List<Date> regDateArray;

	private User user;

	private List<User> users;

	private List<T> generics;

	private List<TestObjectArray<T>> genericObjs;

	private List<TestObjectArray<User>> genericUsers;

	private TestObjectArray<T> genericObj;

	private T generic;

	public TestObjectArray() {
	}

	public TestObjectArray(String id, boolean booleanValue, int intValue, long longValue, float floatValue,
			double doubleValue, String stringValue, List<Byte> bytesValue, Date regDate,
			List<Boolean> booleanValueArray, List<Integer> intValueArray, List<Long> longValueArray,
			List<Float> floatValueArray, List<Double> doubleValueArray, List<String> stringValueArray,
			List<Date> regDateArray, User user, List<User> users, List<T> generics,
			List<TestObjectArray<T>> genericObjs, List<TestObjectArray<User>> genericUsers,
			TestObjectArray<T> genericObj, T generic) {
		this.id = id;
		this.booleanValue = booleanValue;
		this.intValue = intValue;
		this.longValue = longValue;
		this.floatValue = floatValue;
		this.doubleValue = doubleValue;
		this.stringValue = stringValue;
		this.bytesValue = bytesValue;
		this.regDate = regDate;
		this.booleanValueArray = booleanValueArray;
		this.intValueArray = intValueArray;
		this.longValueArray = longValueArray;
		this.floatValueArray = floatValueArray;
		this.doubleValueArray = doubleValueArray;
		this.stringValueArray = stringValueArray;
		this.regDateArray = regDateArray;
		this.user = user;
		this.users = users;
		this.generics = generics;
		this.genericObjs = genericObjs;
		this.genericUsers = genericUsers;
		this.genericObj = genericObj;
		this.generic = generic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 签名
	 * @see java.lang
	 * java.lang
	 */
	public boolean getBooleanValue() {
		return booleanValue;
	}

	/**
	 * 签名
	 * @see java.lang
	 * java.lang
	 */
	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public List<Byte> getBytesValue() {
		return bytesValue;
	}

	public void setBytesValue(List<Byte> bytesValue) {
		this.bytesValue = bytesValue;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public List<Boolean> getBooleanValueArray() {
		return booleanValueArray;
	}

	public void setBooleanValueArray(List<Boolean> booleanValueArray) {
		this.booleanValueArray = booleanValueArray;
	}

	public List<Integer> getIntValueArray() {
		return intValueArray;
	}

	public void setIntValueArray(List<Integer> intValueArray) {
		this.intValueArray = intValueArray;
	}

	public List<Long> getLongValueArray() {
		return longValueArray;
	}

	public void setLongValueArray(List<Long> longValueArray) {
		this.longValueArray = longValueArray;
	}

	public List<Float> getFloatValueArray() {
		return floatValueArray;
	}

	public void setFloatValueArray(List<Float> floatValueArray) {
		this.floatValueArray = floatValueArray;
	}

	public List<Double> getDoubleValueArray() {
		return doubleValueArray;
	}

	public void setDoubleValueArray(List<Double> doubleValueArray) {
		this.doubleValueArray = doubleValueArray;
	}

	public List<String> getStringValueArray() {
		return stringValueArray;
	}

	public void setStringValueArray(List<String> stringValueArray) {
		this.stringValueArray = stringValueArray;
	}

	public List<Date> getRegDateArray() {
		return regDateArray;
	}

	public void setRegDateArray(List<Date> regDateArray) {
		this.regDateArray = regDateArray;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<T> getGenerics() {
		return generics;
	}

	public void setGenerics(List<T> generics) {
		this.generics = generics;
	}

	public List<TestObjectArray<T>> getGenericObjs() {
		return genericObjs;
	}

	public void setGenericObjs(List<TestObjectArray<T>> genericObjs) {
		this.genericObjs = genericObjs;
	}

	public List<TestObjectArray<User>> getGenericUsers() {
		return genericUsers;
	}

	public void setGenericUsers(List<TestObjectArray<User>> genericUsers) {
		this.genericUsers = genericUsers;
	}

	public TestObjectArray<T> getGenericObj() {
		return genericObj;
	}

	public void setGenericObj(TestObjectArray<T> genericObj) {
		this.genericObj = genericObj;
	}

	public T getGeneric() {
		return generic;
	}

	public void setGeneric(T generic) {
		this.generic = generic;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		if (id != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "id", id));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "booleanValue", booleanValue));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "intValue", intValue));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "longValue", longValue));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "floatValue", floatValue));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "doubleValue", doubleValue));

		if (stringValue != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "stringValue", stringValue));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "bytesValue", bytesValue));

		if (regDate != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "regDate", regDate));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "booleanValueArray", booleanValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "intValueArray", intValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "longValueArray", longValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "floatValueArray", floatValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "doubleValueArray", doubleValueArray));

		if (stringValueArray != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "stringValueArray", stringValueArray));
		}

		if (regDateArray != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "regDateArray", regDateArray));
		}

		if (user != null) {
			user.encode(parent + "user.", $list);
		}

		if (users != null && (!users.isEmpty())) {
			for (int i = 0; i < users.size(); i++) {
				users.get(i).encode(parent + "users" + "[" + i + "].", $list);
			}
		}

		if (generics != null && (!generics.isEmpty())) {
			for (int i = 0; i < generics.size(); i++) {
				generics.get(i).encode(parent + "generics" + "[" + i + "].", $list);
			}
		}

		if (genericObjs != null && (!genericObjs.isEmpty())) {
			for (int i = 0; i < genericObjs.size(); i++) {
				genericObjs.get(i).encode(parent + "genericObjs" + "[" + i + "].", $list);
			}
		}

		if (genericUsers != null && (!genericUsers.isEmpty())) {
			for (int i = 0; i < genericUsers.size(); i++) {
				genericUsers.get(i).encode(parent + "genericUsers" + "[" + i + "].", $list);
			}
		}

		if (genericObj != null) {
			genericObj.encode(parent + "genericObj.", $list);
		}

		if (generic != null) {
			generic.encode(parent + "generic.", $list);
		}

		return $list;
	}

	@Override
	public String toString() {
		return "TestObjectList [id=" + id + ",booleanValue=" + booleanValue + ",intValue=" + intValue + ",longValue="
				+ longValue + ",floatValue=" + floatValue + ",doubleValue=" + doubleValue + ",stringValue="
				+ stringValue + ",bytesValue=" + bytesValue + ",regDate=" + regDate + ",booleanValueArray="
				+ booleanValueArray + ",intValueArray=" + intValueArray + ",longValueArray=" + longValueArray
				+ ",floatValueArray=" + floatValueArray + ",doubleValueArray=" + doubleValueArray
				+ ",stringValueArray=" + stringValueArray + ",regDateArray=" + regDateArray + ",user=" + user
				+ ",users=" + users + ",generics=" + generics + ",genericObjs=" + genericObjs + ",genericUsers="
				+ genericUsers + ",genericObj=" + genericObj + ",generic=" + generic + ", ]";
	}
}