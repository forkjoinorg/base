package com.forkjoin.util;

import com.forkjoin.core.junit.BaseTest;

public class ArrayUtilsTest extends BaseTest {
	public void test(){
		assertTrue(ArrayExpandUtils.check(new Object[]{1}, Number.class));
	}
}
