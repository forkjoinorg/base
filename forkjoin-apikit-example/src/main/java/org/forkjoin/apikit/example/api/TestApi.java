package org.forkjoin.apikit.example.api;

import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.ActionType;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.ApiMethod;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.User;
import org.forkjoin.apikit.spring.Result;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;


/**
 * @author zuoge85 on 15/6/11.
 */
@Api
public interface TestApi {

    /**
     * 添加
     */
    @Account(false)
    @ApiMethod(value = "test/", type = ActionType.POST)
    Result<TestObject<User>> create(@Valid TestForm<User> testForm) throws Exception;

    @Account(false)
    @ApiMethod(value = "test/{id}", type = ActionType.GET)
    Result<TestObject<User>> get(@PathVariable String id) throws Exception;

    @Account(false)
    @ApiMethod(value = "test/", type = ActionType.PUT)
    Result<TestObject<User>> update(@Valid TestForm<User> testForm) throws Exception;

    @Account(false)
    @ApiMethod(value = "test/", type = ActionType.PATCH)
    Result<TestObject<User>> patchUpdate(@Valid TestForm<User> testForm) throws Exception;

    @Account(false)
    @ApiMethod(value = "test/{id}", type = ActionType.DELETE)
    Result<Boolean> delete(@PathVariable String id) throws Exception;

    @Account(false)
    @ApiMethod(value = "tests/{id}", type = ActionType.DELETE)
    Result<Integer> deletes(@PathVariable List<String> id) throws Exception;

    @Account(false)
    @ApiMethod(value = "search/{id}/{name}", type = ActionType.GET)
    Result<TestObject<User>> search(@PathVariable String id, @PathVariable String name) throws Exception;

    @Account(false)
    @ApiMethod(value = "testString/{name}", type = ActionType.GET)
    Result<String> testString(@PathVariable String name) throws Exception;

    @Account(false)
    @ApiMethod(value = "testString1/{name}/{age}", type = ActionType.GET)
    Result<String> testString1(@PathVariable String name, @PathVariable String age) throws Exception;
}
