package org.forkjoin.apikit.impl.api;

import org.forkjoin.apikit.core.*;
import org.forkjoin.apikit.impl.api.form.TestForm;
import org.forkjoin.apikit.impl.api.model.TestObject;
import org.forkjoin.apikit.impl.api.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;


/**
 * @author zuoge85 on 15/6/11.
 */
@Api
public interface BaseApi {
    /**
     * @param testForm 测试表单
     */
    @ApiMethod(value = "base/", type = ActionType.POST)
    @Account(value = true, param = "accountName")
    Result<TestObject<User[]>[]> create(@Valid TestForm<User> testForm);

    @ApiMethod(value = "base/{id}", type = ActionType.GET)
    Result<Void> get(@PathVariable String[] ids);

    @ApiMethod("baseUrl/")
    @Account(false)
    Result<User> create(@Valid User user);
}
