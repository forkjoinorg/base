package com.text.api.i;

import org.forkjoin.spring.Result;
import javax.validation.Valid;

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
 * 主要用于限制api Controller 是否实现
 * @author  zuoge85 on 15/6/11.
 */
public interface BaseApiInterface {

	Result<java.util.ArrayList<TestObject<User>>> create(User user, TestForm<User> testForm,
			java.lang.Object accountName) throws Exception;

	Result<Void> get(String id, java.lang.Object user) throws Exception;

	Result<User> create(User user, User user1, java.lang.Object accountName) throws Exception;
}