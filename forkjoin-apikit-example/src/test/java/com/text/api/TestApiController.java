package com.text.api;

import org.forkjoin.apikit.core.AccountParam;
import org.forkjoin.apikit.spring.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import com.text.api.i.*;
import java.lang.Object;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.ActionType;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.ApiMethod;
import com.text.form.TestForm;
import com.text.model.TestObject;
import com.text.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.util.List;

/** 
 * @author  zuoge85 on 15/6/11.
 */
@Controller
@RequestMapping(value = "v1", produces = "application/json")
public class TestApiController implements TestApiInterface {
	/** 
	 * 添加
	 */
	@ResponseBody
	@RequestMapping(value = "test/", method = RequestMethod.POST)
	@Account(false)
	@Override
	public Result<TestObject<User>> create(@Valid TestForm<User> testForm, @AccountParam java.lang.Object user)
			throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "test/{id}", method = RequestMethod.GET)
	@Account(false)
	@Override
	public Result<TestObject<User>> get(@PathVariable String id, @AccountParam java.lang.Object user) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "test/", method = RequestMethod.PUT)
	@Account(false)
	@Override
	public Result<TestObject<User>> update(@Valid TestForm<User> testForm, @AccountParam java.lang.Object user)
			throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "test/", method = RequestMethod.PATCH)
	@Account(false)
	@Override
	public Result<TestObject<User>> patchUpdate(@Valid TestForm<User> testForm, @AccountParam java.lang.Object user)
			throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "test/{id}", method = RequestMethod.DELETE)
	@Account(false)
	@Override
	public Result<Boolean> delete(@PathVariable String id, @AccountParam java.lang.Object user) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "tests/{id}", method = RequestMethod.DELETE)
	@Account(false)
	@Override
	public Result<Integer> deletes(@PathVariable List<String> id, @AccountParam java.lang.Object user) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "search/{id}/{name}", method = RequestMethod.GET)
	@Account(false)
	@Override
	public Result<TestObject<User>> search(@PathVariable String id, @PathVariable String name,
			@AccountParam java.lang.Object user) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "testString/{name}", method = RequestMethod.GET)
	@Account(false)
	@Override
	public Result<String> testString(@PathVariable String name, @AccountParam java.lang.Object user) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "testString1/{name}/{age}", method = RequestMethod.GET)
	@Account(false)
	@Override
	public Result<String> testString1(@PathVariable String name, @PathVariable String age,
			@AccountParam java.lang.Object user) throws Exception {
		return Result.createSuccess();
	}
}
