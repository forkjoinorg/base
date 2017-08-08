package org.forkjoin.apikit.example.api;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.TestObjectList;
import org.forkjoin.apikit.example.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zuoge85 on 15/6/11.
 */
//@Api
@RestController
@RequestMapping(value = "v1")
public class PageApiController {

    @RequestMapping(value = "page/page/{page}-{pageSize}", method = RequestMethod.GET)
    @Account(false)
    public PageResult<TestObject> page(@PathVariable int page, @PathVariable int pageSize) throws Exception {
        PageResult<TestObject> pageResult = PageResult.createPage(10, page, pageSize);
        pageResult.setData(Collections.singletonList(new TestObject()));
        return pageResult;
    }

    @RequestMapping(value = "page/pageString/{page}-{pageSize}", method = RequestMethod.GET)
    @Account(false)
    public PageResult<String> pageString(@PathVariable int page, @PathVariable int pageSize) throws Exception {
        PageResult<String> pageResult = PageResult.createPage(10, page, pageSize);
        pageResult.setData(Collections.singletonList(RandomStringUtils.randomAlphanumeric(10)));
        return pageResult;
    }
}