package org.forkjoin.apikit.example.api;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.lang.Object;
import org.forkjoin.apikit.core.*;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.*;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author  zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class BaseApiController {

	@RequestMapping(value = "base", method = RequestMethod.POST)
	public Result<TestObject<User[]>[]> create(@Valid TestForm<User> testForm, Object accountName) throws Exception {
		return Result.createSuccess();
	}

	@RequestMapping(value = "base/{id}", method = {RequestMethod.POST})
	public Result<Void> get(@PathVariable String id, Object user) throws Exception {
		return Result.createSuccess();
	}

	@RequestMapping(value = "baseUrl/", method = {RequestMethod.GET, RequestMethod.POST})
	public Result<User> create(@Valid User user, Object accountName) throws Exception {
		return Result.createSuccess();
	}
}