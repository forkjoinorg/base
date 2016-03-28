package com.text.api;

import org.forkjoin.apikit.core.AccountParam;
import org.forkjoin.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import com.text.api.i.*;
import java.lang.Object;
import org.forkjoin.api.Account;
import org.forkjoin.api.ActionType;
import org.forkjoin.api.Api;
import org.forkjoin.api.ApiMethod;
import com.text.form.TestForm;
import com.text.model.TestObject;
import com.text.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;

/** 
 * @author  zuoge85 on 15/6/11.
 */
@Controller
@RequestMapping(value = "v1", produces = "application/json")
public class BaseApiController implements BaseApiInterface {
	@ResponseBody
	@RequestMapping(value = "base/", method = RequestMethod.POST)
	@Account(value = true, param = "accountName")
	@Override
	public Result<java.util.ArrayList<TestObject<User>>> create(User user, TestForm<User> testForm,
			@AccountParam java.lang.Object accountName) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "base/{id}", method = RequestMethod.GET)
	@Override
	public Result<Void> get(String id, @AccountParam java.lang.Object user) throws Exception {
		System.out.println(id);
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "baseUrl/", method = RequestMethod.GET)
	@Account(value = true, param = "accountName")
	@Override
	public Result<User> create(User user, User user1, @AccountParam java.lang.Object accountName) throws Exception {
		return Result.createSuccess();
	}
}
