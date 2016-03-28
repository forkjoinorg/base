package org.forkjoin.util;

import org.forkjoin.core.junit.BaseTest;

import java.sql.Date;
import java.util.Map;

/**
 * @author zuoge85 on 14-4-29.
 */
public class JsonUtilsTest extends BaseTest {
    public void test() {
        String serialize = JsonUtils.serialize(new Date(System.currentTimeMillis()));
        System.out.println(serialize);
        System.out.println(JsonUtils.deserialize(serialize, java.sql.Date.class));


        serialize = JsonUtils.serialize(new java.util.Date());
        System.out.println(serialize);
    }
}
