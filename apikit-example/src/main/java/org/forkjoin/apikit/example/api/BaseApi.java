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


/**
 * @author zuoge85 on 15/6/11.
 */
@Api
public interface BaseApi {
    @ApiMethod(value = "base/", type = ActionType.POST)
    @Account(value = true, param = "accountName")
    TestObject<User[]>[] create(@Valid User user, @Valid TestForm<User> testForm);

    @ApiMethod(value = "base/{id}", type = ActionType.GET)
    void get(@PathVariable String id);

    @ApiMethod("baseUrl/")
    @Account(value = true, param = "accountName")
    org.forkjoin.apikit.example.model.User create(@Valid User user, @Valid User user1);
}
