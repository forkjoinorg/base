package org.forkjoin.apikit.example.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.forkjoin.apikit.core.ApiMessage;
import org.forkjoin.apikit.example.BaseControllerTest;
import org.forkjoin.apikit.example.client.form.TestForm;
import org.forkjoin.apikit.example.client.model.TestObject;
import org.forkjoin.apikit.example.client.model.TestObjectList;
import org.forkjoin.apikit.example.client.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * @author zuoge85@gmail.com on 2017/5/11.
 */
public class TestApiControllerTest extends BaseControllerTest {

    public static final int ARRAY_MAX = 100;
    public static final String CHARSET_NAME = "utf8";

    @Test
    public void testVoid() {
        apiManager.testApi.testVoid();
    }

    @Test
    public void testStringData() {
        String str = RandomStringUtils.random(ARRAY_MAX);
        String result = apiManager.testApi.testStringData(str);
        Assert.assertEquals(str, result);
    }

    @Test
    public void testObjectList() throws Exception {
        String str = RandomStringUtils.random(ARRAY_MAX);

        TestForm<User> form = createForm();
        ArrayList<String> stringValueArray = new ArrayList<>();
        stringValueArray.add("sb,sb");
        stringValueArray.add("sb1,sb1");
        form.setStringValueArray(stringValueArray);
        form.setStringValue(form.getStringValue()+",+,sb%");
        TestObjectList<User> data = apiManager.testApi.testObjectListData(form);

        Assert.assertEquals(form.getBooleanValue(), data.getBooleanValue());
        Assert.assertEquals(form.getBooleanValueArray(), data.getBooleanValueArray());
//        Assert.assertArrayEquals(form.getBytesValue(), data.getBytesValue());

        Assert.assertEquals(form.getDoubleValue(), data.getDoubleValue(), 0);
        Assert.assertEquals(form.getDoubleValueArray(), data.getDoubleValueArray());

        Assert.assertEquals(form.getFloatValue(), data.getFloatValue(), 0);
        Assert.assertEquals(form.getFloatValueArray(), data.getFloatValueArray());

        Assert.assertEquals(form.getIntValue(), data.getIntValue(), 0);
        Assert.assertEquals(form.getIntValueArray(), data.getIntValueArray());

        Assert.assertEquals(form.getLongValue(), data.getLongValue(), 0);
        Assert.assertEquals(form.getLongValueArray(), data.getLongValueArray());

        Assert.assertEquals(form.getRegDate(), data.getRegDate());
        Assert.assertEquals(form.getRegDateArray(), data.getRegDateArray());

        Assert.assertEquals(form.getStringValue(), data.getStringValue());
        Assert.assertEquals(form.getStringValueArray(), data.getStringValueArray());


        assertEquals(form.getUser(), data.getUser());
        Assert.assertEquals(form.getUsers().toString(), data.getUsers().toString());
    }

    @Test
    public void testCreate() throws Exception {

        TestForm<User> form = createForm();


        TestObject<User> data = apiManager.testApi.createData(form);

        Assert.assertEquals(form.getBooleanValue(), data.getBooleanValue());
        Assert.assertEquals(form.getBooleanValueArray(), data.getBooleanValueArray());
        Assert.assertArrayEquals(form.getBytesValue(), data.getBytesValue());

        Assert.assertEquals(form.getDoubleValue(), data.getDoubleValue(), 0);
        Assert.assertEquals(form.getDoubleValueArray(), data.getDoubleValueArray());

        Assert.assertEquals(form.getFloatValue(), data.getFloatValue(), 0);
        Assert.assertEquals(form.getFloatValueArray(), data.getFloatValueArray());

        Assert.assertEquals(form.getIntValue(), data.getIntValue(), 0);
        Assert.assertEquals(form.getIntValueArray(), data.getIntValueArray());

        Assert.assertEquals(form.getLongValue(), data.getLongValue(), 0);
        Assert.assertEquals(form.getLongValueArray(), data.getLongValueArray());

        Assert.assertEquals(form.getRegDate(), data.getRegDate());
        Assert.assertEquals(form.getRegDateArray(), data.getRegDateArray());

        Assert.assertEquals(form.getStringValue(), data.getStringValue());
        Assert.assertEquals(form.getStringValueArray(), data.getStringValueArray());


        assertEquals(form.getUser(), data.getUser());
        Assert.assertEquals(form.getUsers().toString(), data.getUsers().toString());

    }

    private TestForm<User> createForm() throws Exception {
        final Random random = new Random();
        TestForm<User> form = new TestForm<>();

        form.setBooleanValue(random.nextBoolean());
        form.setBooleanValueArray(randomArrayList(random::nextBoolean));

        form.setBytesValue(RandomStringUtils.random(random.nextInt(ARRAY_MAX) + 1).getBytes(CHARSET_NAME));

        form.setDoubleValue(random.nextDouble());
        ArrayList<Double> doubleValueArray = randomArrayList(random::nextDouble);
        doubleValueArray.add(Double.MAX_VALUE);
        doubleValueArray.add(Double.MIN_VALUE);
        form.setDoubleValueArray(doubleValueArray);

        form.setFloatValue(random.nextFloat());
        form.setFloatValueArray(randomArrayList(random::nextFloat));

        form.setIntValue(random.nextInt());
        form.setIntValueArray(randomArrayList(random::nextInt));

        form.setLongValue(random.nextInt());
        form.setLongValueArray(randomArrayList(random::nextLong));

        form.setRegDate(new Date());
        form.setRegDateArray(randomArrayList(Date::new));

        form.setStringValue(RandomStringUtils.random(random.nextInt(ARRAY_MAX)));
        form.setStringValueArray(randomArrayList(() -> RandomStringUtils.random(random.nextInt(ARRAY_MAX))));

        form.setUser(newUser());
        form.setUsers(randomArrayList(this::newUser));
        return form;
    }

    private void assertEquals(ApiMessage expected, ApiMessage actual) {
        Assert.assertTrue((expected != null) == (actual != null));
        if(expected != null){
            List<Map.Entry<String, Object>> expectedList = new ArrayList<>();
            expected.encode("", expectedList);

            List<Map.Entry<String, Object>> actualList = new ArrayList<>();
            expected.encode("", actualList);

            Assert.assertEquals(expectedList, actualList);
        }
    }

    private User newUser() {
        return new User();
    }

    public <T> ArrayList<T> randomArrayList(Callable<T> back) throws Exception {
        ArrayList<T> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(ARRAY_MAX) + 1; i++) {
            list.add(back.call());
        }
        return list;
    }
}
