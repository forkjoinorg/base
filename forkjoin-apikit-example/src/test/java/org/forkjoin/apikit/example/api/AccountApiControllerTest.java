package org.forkjoin.apikit.example.api;

import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.BaseControllerTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zuoge85@gmail.com on 2017/5/11.
 */
public class AccountApiControllerTest extends BaseControllerTest {

    public static final int ARRAY_MAX = 100;
    public static final String CHARSET_NAME = "utf8";

    @Test
    public void testNotLoginData() {
        /*
         * 未登陆
         */
        httpClientAdapter.setAccountToken(null);
        accountApi.testNotLoginData();
        Result<Void> result = accountApi.testLogin();
        Assert.assertEquals(result.getStatus(), Result.ACCOUNT_ERROR);
    }

    @Test
    public void testLoginData() {
        /*
         * 登陆
         */
        String token = accountApi.loginData();
        httpClientAdapter.setAccountToken(token);
        accountApi.testLoginData();
    }
}
