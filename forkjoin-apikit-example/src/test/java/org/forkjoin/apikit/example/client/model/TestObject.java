package org.forkjoin.apikit.example.client.model;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @author  zuoge85 on 15/6/17.
 */
public class TestObject<T extends ApiMessage> implements ApiMessage {

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

	private byte[] bytesValue;

	private Date regDate;

	private java.util.ArrayList<Boolean> booleanValueArray;

	private java.util.ArrayList<Integer> intValueArray;

	private java.util.ArrayList<Long> longValueArray;

	private java.util.ArrayList<Float> floatValueArray;

	private java.util.ArrayList<Double> doubleValueArray;

	private java.util.ArrayList<String> stringValueArray;

	private java.util.ArrayList<Date> regDateArray;

	private User user;

	private java.util.ArrayList<User> users;

	private java.util.ArrayList<T> generics;

	private java.util.ArrayList<TestObject<T>> genericObjs;

	private java.util.ArrayList<TestObject<User>> genericUsers;

	private TestObject<T> genericObj;

	private T generic;

	public TestObject() {
	}

	public TestObject(String id, boolean booleanValue, int intValue, long longValue, float floatValue,
			double doubleValue, String stringValue, byte[] bytesValue, Date regDate,
			java.util.ArrayList<Boolean> booleanValueArray, java.util.ArrayList<Integer> intValueArray,
			java.util.ArrayList<Long> longValueArray, java.util.ArrayList<Float> floatValueArray,
			java.util.ArrayList<Double> doubleValueArray, java.util.ArrayList<String> stringValueArray,
			java.util.ArrayList<Date> regDateArray, User user, java.util.ArrayList<User> users,
			java.util.ArrayList<T> generics, java.util.ArrayList<TestObject<T>> genericObjs,
			java.util.ArrayList<TestObject<User>> genericUsers, TestObject<T> genericObj, T generic) {
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

	public byte[] getBytesValue() {
		return bytesValue;
	}

	public void setBytesValue(byte[] bytesValue) {
		this.bytesValue = bytesValue;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public java.util.ArrayList<Boolean> getBooleanValueArray() {
		return booleanValueArray;
	}

	public void setBooleanValueArray(java.util.ArrayList<Boolean> booleanValueArray) {
		this.booleanValueArray = booleanValueArray;
	}

	public java.util.ArrayList<Integer> getIntValueArray() {
		return intValueArray;
	}

	public void setIntValueArray(java.util.ArrayList<Integer> intValueArray) {
		this.intValueArray = intValueArray;
	}

	public java.util.ArrayList<Long> getLongValueArray() {
		return longValueArray;
	}

	public void setLongValueArray(java.util.ArrayList<Long> longValueArray) {
		this.longValueArray = longValueArray;
	}

	public java.util.ArrayList<Float> getFloatValueArray() {
		return floatValueArray;
	}

	public void setFloatValueArray(java.util.ArrayList<Float> floatValueArray) {
		this.floatValueArray = floatValueArray;
	}

	public java.util.ArrayList<Double> getDoubleValueArray() {
		return doubleValueArray;
	}

	public void setDoubleValueArray(java.util.ArrayList<Double> doubleValueArray) {
		this.doubleValueArray = doubleValueArray;
	}

	public java.util.ArrayList<String> getStringValueArray() {
		return stringValueArray;
	}

	public void setStringValueArray(java.util.ArrayList<String> stringValueArray) {
		this.stringValueArray = stringValueArray;
	}

	public java.util.ArrayList<Date> getRegDateArray() {
		return regDateArray;
	}

	public void setRegDateArray(java.util.ArrayList<Date> regDateArray) {
		this.regDateArray = regDateArray;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public java.util.ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(java.util.ArrayList<User> users) {
		this.users = users;
	}

	public java.util.ArrayList<T> getGenerics() {
		return generics;
	}

	public void setGenerics(java.util.ArrayList<T> generics) {
		this.generics = generics;
	}

	public java.util.ArrayList<TestObject<T>> getGenericObjs() {
		return genericObjs;
	}

	public void setGenericObjs(java.util.ArrayList<TestObject<T>> genericObjs) {
		this.genericObjs = genericObjs;
	}

	public java.util.ArrayList<TestObject<User>> getGenericUsers() {
		return genericUsers;
	}

	public void setGenericUsers(java.util.ArrayList<TestObject<User>> genericUsers) {
		this.genericUsers = genericUsers;
	}

	public TestObject<T> getGenericObj() {
		return genericObj;
	}

	public void setGenericObj(TestObject<T> genericObj) {
		this.genericObj = genericObj;
	}

	public T getGeneric() {
		return generic;
	}

	public void setGeneric(T generic) {
		this.generic = generic;
	}

	public void addBooleanValueArray(boolean booleanValueArray) {
		if (this.booleanValueArray == null) {
			this.booleanValueArray = new java.util.ArrayList<Boolean>();
		}
		this.booleanValueArray.add(booleanValueArray);
	}

	public void addIntValueArray(int intValueArray) {
		if (this.intValueArray == null) {
			this.intValueArray = new java.util.ArrayList<Integer>();
		}
		this.intValueArray.add(intValueArray);
	}

	public void addLongValueArray(long longValueArray) {
		if (this.longValueArray == null) {
			this.longValueArray = new java.util.ArrayList<Long>();
		}
		this.longValueArray.add(longValueArray);
	}

	public void addFloatValueArray(float floatValueArray) {
		if (this.floatValueArray == null) {
			this.floatValueArray = new java.util.ArrayList<Float>();
		}
		this.floatValueArray.add(floatValueArray);
	}

	public void addDoubleValueArray(double doubleValueArray) {
		if (this.doubleValueArray == null) {
			this.doubleValueArray = new java.util.ArrayList<Double>();
		}
		this.doubleValueArray.add(doubleValueArray);
	}

	public void addStringValueArray(String stringValueArray) {
		if (this.stringValueArray == null) {
			this.stringValueArray = new java.util.ArrayList<String>();
		}
		this.stringValueArray.add(stringValueArray);
	}

	public void addRegDateArray(Date regDateArray) {
		if (this.regDateArray == null) {
			this.regDateArray = new java.util.ArrayList<Date>();
		}
		this.regDateArray.add(regDateArray);
	}

	public void addUsers(User users) {
		if (this.users == null) {
			this.users = new java.util.ArrayList<User>();
		}
		this.users.add(users);
	}

	public void addGenerics(T generics) {
		if (this.generics == null) {
			this.generics = new java.util.ArrayList<T>();
		}
		this.generics.add(generics);
	}

	public void addGenericObjs(TestObject<T> genericObjs) {
		if (this.genericObjs == null) {
			this.genericObjs = new java.util.ArrayList<TestObject<T>>();
		}
		this.genericObjs.add(genericObjs);
	}

	public void addGenericUsers(TestObject<User> genericUsers) {
		if (this.genericUsers == null) {
			this.genericUsers = new java.util.ArrayList<TestObject<User>>();
		}
		this.genericUsers.add(genericUsers);
	}

	@Override
	public List<Entry<String, Object>> encode(String $parent, List<Entry<String, Object>> $list) {

		if (id != null) {
			$list.add(new SimpleImmutableEntry<String, Object>($parent + "id", id));
		}

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "booleanValue", booleanValue));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "intValue", intValue));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "longValue", longValue));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "floatValue", floatValue));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "doubleValue", doubleValue));

		if (stringValue != null) {
			$list.add(new SimpleImmutableEntry<String, Object>($parent + "stringValue", stringValue));
		}

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "bytesValue", bytesValue));

		if (regDate != null) {
			$list.add(new SimpleImmutableEntry<String, Object>($parent + "regDate", regDate));
		}

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "booleanValueArray", booleanValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "intValueArray", intValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "longValueArray", longValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "floatValueArray", floatValueArray));

		$list.add(new SimpleImmutableEntry<String, Object>($parent + "doubleValueArray", doubleValueArray));

		if (stringValueArray != null) {
			$list.add(new SimpleImmutableEntry<String, Object>($parent + "stringValueArray", stringValueArray));
		}

		if (regDateArray != null) {
			$list.add(new SimpleImmutableEntry<String, Object>($parent + "regDateArray", regDateArray));
		}

		if (user != null) {
			user.encode($parent + "user.", $list);
		}

		if (users != null && (!users.isEmpty())) {
			for (int i = 0; i < users.size(); i++) {
				users.get(i).encode($parent + "users" + "[" + i + "].", $list);
			}
		}

		if (generics != null && (!generics.isEmpty())) {
			for (int i = 0; i < generics.size(); i++) {
				generics.get(i).encode($parent + "generics" + "[" + i + "].", $list);
			}
		}

		if (genericObjs != null && (!genericObjs.isEmpty())) {
			for (int i = 0; i < genericObjs.size(); i++) {
				genericObjs.get(i).encode($parent + "genericObjs" + "[" + i + "].", $list);
			}
		}

		if (genericUsers != null && (!genericUsers.isEmpty())) {
			for (int i = 0; i < genericUsers.size(); i++) {
				genericUsers.get(i).encode($parent + "genericUsers" + "[" + i + "].", $list);
			}
		}

		if (genericObj != null) {
			genericObj.encode($parent + "genericObj.", $list);
		}

		if (generic != null) {
			generic.encode($parent + "generic.", $list);
		}

		return $list;
	}

	@Override
	public String toString() {
		return "TestObject [id=" + id + ",booleanValue=" + booleanValue + ",intValue=" + intValue + ",longValue="
				+ longValue + ",floatValue=" + floatValue + ",doubleValue=" + doubleValue + ",stringValue="
				+ stringValue + ",bytesValue=length:" + (bytesValue == null ? -1 : bytesValue.length) + ",regDate="
				+ regDate + ",booleanValueArray=size:" + (booleanValueArray == null ? -1 : booleanValueArray.size())
				+ ",intValueArray=size:" + (intValueArray == null ? -1 : intValueArray.size())
				+ ",longValueArray=size:" + (longValueArray == null ? -1 : longValueArray.size())
				+ ",floatValueArray=size:" + (floatValueArray == null ? -1 : floatValueArray.size())
				+ ",doubleValueArray=size:" + (doubleValueArray == null ? -1 : doubleValueArray.size())
				+ ",stringValueArray=size:" + (stringValueArray == null ? -1 : stringValueArray.size())
				+ ",regDateArray=size:" + (regDateArray == null ? -1 : regDateArray.size()) + ",user=" + user
				+ ",users=size:" + (users == null ? -1 : users.size()) + ",generics=size:"
				+ (generics == null ? -1 : generics.size()) + ",genericObjs=size:"
				+ (genericObjs == null ? -1 : genericObjs.size()) + ",genericUsers=size:"
				+ (genericUsers == null ? -1 : genericUsers.size()) + ",genericObj=" + genericObj + ",generic="
				+ generic + ", ]";
	}
}