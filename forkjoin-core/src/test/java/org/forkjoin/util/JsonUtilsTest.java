package org.forkjoin.util;

import org.forkjoin.core.junit.BaseTest;

import java.util.Date;
import java.util.Map;

/**
 * @author zuoge85 on 14-4-29.
 */
public class JsonUtilsTest extends BaseTest {
    public void test() {
        String serialize = JsonUtils.serialize(new Date(System.currentTimeMillis()));
        System.out.println(serialize);
        System.out.println(JsonUtils.deserialize(serialize, java.util.Date.class));


        serialize = JsonUtils.serialize("\"\"");
        System.out.println(serialize);
    }
}
