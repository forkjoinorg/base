package org.forkjoin.apikit.example.api;

import org.forkjoin.apikit.client.Callback;
import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.apikit.example.BaseControllerTest;
import org.forkjoin.apikit.example.client.model.TestObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zuoge85@gmail.com on 2017/6/15.
 */
public class PageApiControllerTest extends BaseControllerTest{

    public static final int PAGE = 1;
    public static final int PAGE_SIZE = 100;

    @Test
    public void page() throws Exception {
        PageResult<TestObject> page = apiManager.pageApi.page(PAGE, PAGE_SIZE);
        Assert.assertNotNull(page);
        Assert.assertNotNull(page.getData());
        Assert.assertEquals(page.getData().size(),1);
        Assert.assertEquals(page.getPage(),PAGE);
        Assert.assertEquals(page.getPageSize(),PAGE_SIZE);
    }

    @Test
    public void pageString() throws Exception {
        PageResult<String> page = apiManager.pageApi.pageString(PAGE, PAGE_SIZE);
        Assert.assertNotNull(page);
        Assert.assertNotNull(page.getData());
        Assert.assertEquals(page.getData().size(),1);
        Assert.assertEquals(page.getPage(),PAGE);
        Assert.assertEquals(page.getPageSize(),PAGE_SIZE);
    }
}