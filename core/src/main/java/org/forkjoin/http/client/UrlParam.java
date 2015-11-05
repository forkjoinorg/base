package org.forkjoin.http.client;

import org.apache.http.NameValuePair;

public class UrlParam implements NameValuePair {

	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public UrlParam(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public UrlParam() {
	}

}
