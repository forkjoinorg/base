package org.forkjoin.apikit.example.api.controller;

import org.forkjoin.apikit.core.AccountParam;
import org.forkjoin.apikit.spring.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import org.forkjoin.apikit.example.api.*;

import java.lang.Object;
import org.forkjoin.apikit.core.*;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;

/**
 * @author  zuoge85 on 15/6/11.
 */
@Controller
@RequestMapping(value = "v1", produces = "application/json")
public class BaseApiController implements BaseApi {

	@ResponseBody
	@RequestMapping(value = "base/", method = RequestMethod.POST)
	@Account(value = true, param = "accountName")
	@Override
	public Result<TestObject<User[]>[]> create(@Valid TestForm<User> testForm, Object accountName) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "base/{id}", method = RequestMethod.GET)
	@Override
	public Result<Void> get(@PathVariable String id, Object user) throws Exception {
		return Result.createSuccess();
	}

	@ResponseBody
	@RequestMapping(value = "baseUrl/", method = RequestMethod.GET)
	@Account(value = true, param = "accountName")
	@Override
	public Result<User> create(@Valid User user, Object accountName) throws Exception {
		return Result.createSuccess();
	}
}