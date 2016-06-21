package org.forkjoin.util;

import org.forkjoin.core.junit.BaseTest;

import java.util.Map;

/**
 * @author zuoge85 on 14-4-29.
 */
public class JsonTest extends BaseTest {
    public void test() {
        JsonUtils.deserialize("{\"attackRange\":200}", Map.class);

    }
}
