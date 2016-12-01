package org.forkjoin.apikit.example.api;

import org.forkjoin.apikit.core.*;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;


/**
 * @author zuoge85 on 15/6/11.
 */
@Api
public interface BaseApi {
    @ApiMethod(value = "base/", type = ActionType.POST)
    @Account(value = true, param = "accountName")
    Result<TestObject<User[]>[]> create(@Valid TestForm<User> testForm, java.lang.Object accountName) throws Exception;

    @ApiMethod(value = "base/{id}", type = ActionType.GET)
    Result<Void> get(@PathVariable String id, java.lang.Object user) throws Exception;

    @ApiMethod("baseUrl/")
    @Account(value = true, param = "accountName")
    Result<User> create(@Valid User user,  java.lang.Object accountName) throws Exception;
}
