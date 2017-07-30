package org.forkjoin.apikit.example.api;

import org.apache.commons.lang3.ArrayUtils;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.TestObjectList;
import org.forkjoin.apikit.example.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class TestNoResultApiController {

    @RequestMapping(value = "noResult/testObjectList", method = {RequestMethod.POST})
    @Account(false)
    public TestObjectList<User> testObjectList(@Valid TestForm<User> testForm) throws Exception {
        TestObjectList<User> user = new TestObjectList<>();
        BeanUtils.copyProperties(testForm, user);
        user.setBooleanValueArray(Arrays.asList(ArrayUtils.toObject(testForm.getBooleanValueArray())));
        user.setIntValueArray(Arrays.asList(ArrayUtils.toObject(testForm.getIntValueArray())));
        user.setDoubleValueArray(Arrays.asList(ArrayUtils.toObject(testForm.getDoubleValueArray())));
        user.setFloatValueArray(Arrays.asList(ArrayUtils.toObject(testForm.getFloatValueArray())));
        user.setLongValueArray(Arrays.asList(ArrayUtils.toObject(testForm.getLongValueArray())));

        user.setStringValueArray(Arrays.asList(testForm.getStringValueArray()));
        user.setRegDateArray(Arrays.asList(testForm.getRegDateArray()));
        user.setUsers(Arrays.asList(testForm.getUsers()));

        return user;
    }

    /**
     * 添加
     */
    @RequestMapping(value = "noResult/test", method = {RequestMethod.POST})
    @Account(false)
    public TestObject<User> create(@Valid TestForm<User> testForm) throws Exception {
        TestObject<User> user = new TestObject<>();
        BeanUtils.copyProperties(testForm, user);
        return user;
    }

    @RequestMapping(value = "noResult/test/{id}", method = RequestMethod.GET)
    @Account(false)
    public TestObject<User> get(@PathVariable String id) throws Exception {
        return null;
    }

    @RequestMapping(value = "noResult/test", method = RequestMethod.PUT)
    @Account(false)
    public TestObject<User> update(@Valid TestForm<User> testForm) throws Exception {
        return null;
    }

    @RequestMapping(value = "noResult/test", method = RequestMethod.PATCH)
    @Account(false)
    public TestObject<User> patchUpdate(@Valid TestForm<User> testForm) throws Exception {
        return null;
    }

    @RequestMapping(value = "noResult/test/{id}", method = RequestMethod.DELETE)
    @Account(false)
    public Boolean delete(@PathVariable String id) throws Exception {
        return null;
    }

    @RequestMapping(value = "noResult/tests/{id}", method = RequestMethod.DELETE)
    @Account(false)
    public Integer deletes(@PathVariable List<String> id) throws Exception {
        return null;
    }

    @RequestMapping(value = "noResult/search/{id}/{name}", method = RequestMethod.GET)
    @Account(false)
    public TestObject<User> search(@PathVariable String id, @PathVariable String name) throws Exception {
        return null;
    }

    @RequestMapping(value = "noResult/testString/{name}", method = RequestMethod.GET)
    @Account(false)
    public String testString(@PathVariable String name) throws Exception {
        return name;
    }

    @RequestMapping(value = "noResult/testString1/{name}/{age}", method = RequestMethod.GET)
    @Account(false)
    public String testString1(@PathVariable String name, @PathVariable String age) throws Exception {
        return null;
    }

    @RequestMapping(value = "noResult/page", method = RequestMethod.GET)
    @Account(false)
    public PageResult<TestObject> page() throws Exception {
        return null;
    }
}