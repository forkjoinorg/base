package org.forkjoin.util;

import org.forkjoin.core.junit.BaseTest;

import java.io.UnsupportedEncodingException;


public class BitTest extends BaseTest {

	public void test(){
		int i =0;
		i=BitUtils.set(i, 2);
		i=BitUtils.set(i, 3);
		System.out.println(Integer.toBinaryString(2));
	}

	public void test1() throws UnsupportedEncodingException {
		String c = "👿";
		byte[] utf8s = c.getBytes("utf8");
		byte[] utf32s = c.getBytes("utf32");
		byte[] utf16s = c.getBytes("utf16");
		byte[] gbks = c.getBytes("gbk");
		char[] chars = c.toCharArray();
		System.out.println(c.length());
	}
}
