package org.forkjoin.apikit.impl.api;

import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.impl.api.form.TestForm;
import org.forkjoin.apikit.impl.api.model.TestObject;
import org.forkjoin.apikit.impl.api.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;


/**
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class DocApiController {
    /**
     * 后台管理登录接口
     *
     * @param form
     * @return token登录令牌
     * @throws Exception
     */
    @RequestMapping(value = "account/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Account(false)
    public String login(@Valid TestForm<User> form) throws Exception {
        return null;
    }

    /**
     * 创建接口
     *
     * @param testForm 测试表单
     */
    @RequestMapping(value = "doc/", method = RequestMethod.POST)
    public Result<TestObject<User[]>[]> create(@Valid TestForm testForm) {
        return Result.createSuccess();
    }

    /**
     * 测试返回list
     *
     * @return
     */
    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public List<User> list() {
        return Collections.emptyList();
    }

    /**
     * 测试返回数组
     *
     * @return
     */
    @RequestMapping(value = "array/", method = RequestMethod.GET)
    public User[] array() {
        return null;
    }

    /**
     * 测试返回字符串数组
     *
     * @return 字符串数组
     */
    @RequestMapping(value = "stringArray/", method = RequestMethod.GET)
    public String[] stringArray() {
        return null;
    }

    /**
     * 测试返回字符串List
     *
     * @return 字符串List
     */
    @RequestMapping(value = "stringList/", method = RequestMethod.GET)
    public List<String> stringList() {
        return null;
    }

    /**
     * 测试返回Double数组
     * @return Double数组
     */
    @RequestMapping(value = "stringDoubleArray/", method = RequestMethod.GET)
    public Double[] stringDoubleArray() {
        return null;
    }

    /**
     * 测试返回DoubleList
     * @return DoubleList
     */
    @RequestMapping(value = "stringDoubleList/", method = RequestMethod.GET)
    public List<Double> stringDoubleList() {
        return null;
    }

    /**
     * 测试文档翻页解析
     * 这里是详细<b>注释<b/>
     *
     * @param page     当前页码
     *                 获取当前页码
     * @param pageSize 页大小
     */
    @RequestMapping(value = "base/page/{page}/{pageSize}", method = RequestMethod.GET)
    public PageResult<User> page(@PathVariable int page, @PathVariable int pageSize) {
        return null;
    }


    @RequestMapping(value = "base/{id}", method = {RequestMethod.GET})
    public void get(@PathVariable String[] id) {
    }

    @RequestMapping(value = "baseUrl/user", method = {RequestMethod.GET, RequestMethod.POST})
    @Account(false)
    public Result<User> createUser(@Valid User user) {
        return Result.createSuccess();
    }
}
