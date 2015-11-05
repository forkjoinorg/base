package org.forkjoin.oel;

import org.forkjoin.core.junit.BaseTest;
import org.forkjoin.el.UnknownKeyException;
import org.forkjoin.el.ElEngine;
import org.forkjoin.el.exp.LongExpression;
import org.forkjoin.util.RandomUtils;
import org.forkjoin.util.TimeSpan;

import java.util.Date;

/**
 * el 测试用例
 * @author zuoge85
 *
 */
public class ElTest extends BaseTest {
	private static final int NUMS = 100000000;

	public TestObject createObj(){
		
		TestObject obj = new TestObject();
		Date date = new Date();
		int number = RandomUtils.randInt();
		String string = "测试用例";
		
		obj.dateField =date;
		obj.setDateProperty(date);
		
		obj.intField =number;
		obj.setIntProperty(number);
		
		obj.stringField = string;
		obj.setStringProperty(string);
		
		obj.dateMethod(date);
		return obj;
	}
	
	public void testBasObject() throws UnknownKeyException {
		ElEngine oel = ElEngine.getInstance();
		TestObject obj = createObj();
		
		Date date = obj.dateField;
		int number = obj.intField;
		String string = obj.stringField;
		
		assertEquals(date, oel.el(obj, "dateProperty"));
		assertEquals(date.getTime(), (long)oel.el(obj, "dateProperty.time"));
		assertEquals(date, (Date)oel.el(obj, "dateField"));
		assertEquals(date.getTime(), (long)oel.el(obj, "dateField.time"));
		assertEquals(date, (Date)oel.el(obj, "dateMethod()"));
		assertEquals(date.getTime(), (long)oel.el(obj, "dateMethod().time"));
		
		assertEquals(number, (int) oel.el(obj, "intField"));
		assertEquals(number, (int)oel.el(obj, "intProperty"));
		
		assertEquals(string, oel.el(obj, "stringField"));
		assertEquals(string, oel.el(obj, "stringProperty"));
	}

	public void testObj() throws UnknownKeyException{
		ElEngine oel = ElEngine.getInstance();
		TestObject obj = createObj();
		
		Date date = obj.dateField;
		int number = obj.intField;
		String string = obj.stringField;
		obj.objRef = date;
		assertEquals(date.getTime(), (long)oel.el(obj, "objRef.time"));
		
		
		assertEquals(date.getTime(), (long)oel.el(obj, "objRef.time"));
	}
	public void testTime() throws UnknownKeyException, InterruptedException{
		TestObject obj = createObj();
		obj.obj = obj;
		ElEngine oel = ElEngine.getInstance();
		TimeSpan ts = new TimeSpan();
		
		Object o = oel.el(obj, "obj.obj.obj.obj.obj.dateMethod().time");
		System.gc();
		
		ts.start();
		System.out.println("---------------------");
		//Object longElObj = oel.compile(obj, "obj.obj.obj.obj.obj.dateMethod().time");
		LongExpression el = oel.<Long, LongExpression>compile(obj.getClass(), "obj.obj.obj.obj.obj.dateMethod().time");
		for (int i = 0; i <NUMS; i++) {
			Long l = oel.<Long>el(obj, "obj.obj.obj.obj.obj.dateMethod().time");
			//long l = el.longEl(obj);
			//Long l = el.el(obj);
		}
		System.out.println(ts.end());
		ts.start();
		
		for (int i = 0; i <NUMS; i++) {
			Long l = obj.obj.obj.obj.obj.dateMethod().getTime();
		}
		System.out.println(ts.end());
	}
}
