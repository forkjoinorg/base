package org.forkjoin.apikit.impl.api;

import org.forkjoin.apikit.core.*;
import org.forkjoin.apikit.impl.api.form.TestForm;
import org.forkjoin.apikit.impl.api.model.TestObject;
import org.forkjoin.apikit.impl.api.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class BaseApiController {
    /**
     * @param testForm 测试表单
     */
    @RequestMapping(value = "base/", method = RequestMethod.POST)
    public Result<TestObject<User[]>[]> create(@Valid TestForm<User> testForm){
        return Result.createSuccess();
    }

    @RequestMapping(value = "base/{id}", method = {RequestMethod.GET})
    public  Result<Void> get(@PathVariable String[] id) {
        return Result.createSuccess();
    }

    @RequestMapping(value = "baseUrl/", method = {RequestMethod.GET, RequestMethod.POST})
    @Account(false)
    public Result<User> create(@Valid User user) {
        return Result.createSuccess();
    }

}
