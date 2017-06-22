package org.forkjoin.apikit.example.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.forkjoin.apikit.core.ApiMessage;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.BaseControllerTest;
import org.forkjoin.apikit.example.client.form.TestForm;
import org.forkjoin.apikit.example.client.model.TestObject;
import org.forkjoin.apikit.example.client.model.TestObjectList;
import org.forkjoin.apikit.example.client.model.User;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * @author zuoge85@gmail.com on 2017/5/11.
 */
public class AccountApiControllerTest extends BaseControllerTest {

    public static final int ARRAY_MAX = 100;
    public static final String CHARSET_NAME = "utf8";

    @Test
    public void testNotLoginData() {
        /**
         * 未登陆
         */
        httpClientAdapter.setAccountToken(null);
        apiManager.accountApi.testNotLoginData();
        Result<Void> result = apiManager.accountApi.testLogin();
        Assert.assertEquals(result.getStatus(), Result.ACCOUNT_ERROR);
    }

    @Test
    public void testLoginData() {
        /**
         * 登陆
         */
        String token = apiManager.accountApi.loginData();
        httpClientAdapter.setAccountToken(token);
        apiManager.accountApi.testLoginData();
    }
}
