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
import java.util.List;

/**
 * 主要用于限制api Controller 是否实现
 * @author  zuoge85 on 15/6/11.
 */
public interface TestApiInterface {

	/**
	 * 添加
	 */
	Result<TestObject<User>> create(TestForm<User> testForm, java.lang.Object user) throws Exception;

	Result<TestObject<User>> get(String id, java.lang.Object user) throws Exception;

	Result<TestObject<User>> update(TestForm<User> testForm, java.lang.Object user) throws Exception;

	Result<TestObject<User>> patchUpdate(TestForm<User> testForm, java.lang.Object user) throws Exception;

	Result<Boolean> delete(String id, java.lang.Object user) throws Exception;

	Result<Integer> deletes(List<String> id, java.lang.Object user) throws Exception;

	Result<TestObject<User>> search(String id, String name, java.lang.Object user) throws Exception;

	Result<String> testString(String name, java.lang.Object user) throws Exception;

	Result<String> testString1(String name, String age, java.lang.Object user) throws Exception;
}