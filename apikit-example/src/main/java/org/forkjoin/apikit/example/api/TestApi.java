package org.forkjoin.apikit.example.api;

import org.forkjoin.api.Account;
import org.forkjoin.api.ActionType;
import org.forkjoin.api.Api;
import org.forkjoin.api.ApiMethod;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;


/**
 *
 * @author zuoge85 on 15/6/11.
 */
@Api
public interface TestApi {

    /**
     * 添加
     */
    @Account(false)
    @ApiMethod(value = "test/", type = ActionType.CREATE)
    TestObject<User> create(@Valid TestForm<User> testForm);

    @Account(false)
    @ApiMethod(value = "test/{id}", type = ActionType.GET)
    TestObject<User> get(@PathVariable String id);

    @Account(false)
    @ApiMethod(value = "test/", type = ActionType.UPDATE)
    TestObject<User> update(@Valid TestForm<User> testForm);

    @Account(false)
    @ApiMethod(value = "test/", type = ActionType.PATCH)
    TestObject<User> patchUpdate(@Valid TestForm<User> testForm);

    @Account(false)
    @ApiMethod(value = "test/{id}", type = ActionType.DELETE)
    Boolean delete(@PathVariable String id);

    @Account(false)
    @ApiMethod(value = "tests/{id}", type = ActionType.DELETE)
    Integer deletes(@PathVariable List<String> id);

    @Account(false)
    @ApiMethod(value = "search/{id}/{name}", type = ActionType.GET)
    TestObject<User> search(@PathVariable String id, @PathVariable String name);

    @Account(false)
    @ApiMethod(value = "testString/{name}", type = ActionType.GET)
    String testString(@PathVariable String name);

    @Account(false)
    @ApiMethod(value = "testString1/{name}/{age}", type = ActionType.GET)
    String testString1(@PathVariable String name, @PathVariable String age);
}
