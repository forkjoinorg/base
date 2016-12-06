package org.forkjoin.apikit.example.api;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.forkjoin.apikit.core.*;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author  zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class TestApiController{

	/**
	 * 添加
	 */
	@RequestMapping(value = "test/", method = {RequestMethod.POST,RequestMethod.GET})
	@Account(false)
	public Result<TestObject<User>> create(@Valid TestForm<User> testForm) throws Exception {
		return Result.createSuccess();
	}

	@RequestMapping(value = "test/{id}", method = RequestMethod.GET)
	@Account(false)
	public Result<TestObject<User>> get(@PathVariable String id) throws Exception {
		return Result.createSuccess();
	}

	@RequestMapping(value = "test/", method = RequestMethod.PUT)
	@Account(false)
	public Result<TestObject<User>> update(@Valid TestForm<User> testForm) throws Exception {
		return Result.createSuccess();
	}

	@RequestMapping(value = "test/", method = RequestMethod.PATCH)
	@Account(false)
	public Result<TestObject<User>> patchUpdate(@Valid TestForm<User> testForm) throws Exception {
		return Result.createSuccess();
	}

	@RequestMapping(value = "test/{id}", method = RequestMethod.DELETE)
	@Account(false)
	public Result<Boolean> delete(@PathVariable String id) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "tests/{id}", method = RequestMethod.DELETE)
	@Account(false)
	public Result<Integer> deletes(@PathVariable List<String> id) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "search/{id}/{name}", method = RequestMethod.GET)
	@Account(false)
	public Result<TestObject<User>> search(@PathVariable String id, @PathVariable String name) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "testString/{name}", method = RequestMethod.GET)
	@Account(false)
	public Result<String> testString(@PathVariable String name) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "testString1/{name}/{age}", method = RequestMethod.GET)
	@Account(false)
	public Result<String> testString1(@PathVariable String name, @PathVariable String age) throws Exception {
		return Result.createSuccess();
	}
}