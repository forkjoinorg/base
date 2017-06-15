package org.forkjoin.apikit.example.client.api;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import org.forkjoin.apikit.client.*;

import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.client.form.TestForm;
import org.forkjoin.apikit.example.client.model.TestObject;
import org.forkjoin.apikit.example.client.model.User;

/**
 * @author   zuoge85 on 15/6/11.
 */
public class BaseApi {
	private HttpClientAdapter httpClientAdapter;

	public BaseApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;create</li>
	 * <li><b>Form:</b>Objectcreate</li>
	 * <li><b>Model:</b> java.util.ArrayList&lt;TestObject&lt;User&gt;&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see java.util.ArrayList&lt;TestObject&lt;User&gt;&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public java.util.ArrayList<TestObject<User>> createData(TestForm<User> testForm) {
		Result<java.util.ArrayList<TestObject<User>>> result = create(testForm);
		if (!result.isSuccess()) {
			Exception ex = result.getException();
			if (ex != null) {
				throw new RuntimeException(ex.getMessage(), ex);
			} else {
				throw new RuntimeException(result.toString());
			}
		}
		return result.getData();
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;create</li>
	 * <li><b>Form:</b>Objectcreate</li>
	 * <li><b>Model:</b> java.util.ArrayList&lt;TestObject&lt;User&gt;&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see java.util.ArrayList&lt;TestObject&lt;User&gt;&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Result<java.util.ArrayList<TestObject<User>>> create(TestForm<User> testForm) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("base", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter
				.<Result<java.util.ArrayList<TestObject<User>>>, java.util.ArrayList<TestObject<User>>> request("POST",
						_url, _form, _0Type, true);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;create</li>
	 * <li><b>Form:</b>Objectcreate</li>
	 * <li><b>Model:</b> java.util.ArrayList&lt;TestObject&lt;User&gt;&gt;</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see java.util.ArrayList&lt;TestObject&lt;User&gt;&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Future<?> create(TestForm<User> testForm,
			Callback<Result<java.util.ArrayList<TestObject<User>>>, java.util.ArrayList<TestObject<User>>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("base", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("POST", _url, _form, _0Type, true, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Form:</b>Objectget</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Void getData(String id) {
		Result<Void> result = get(id);
		if (!result.isSuccess()) {
			Exception ex = result.getException();
			if (ex != null) {
				throw new RuntimeException(ex.getMessage(), ex);
			} else {
				throw new RuntimeException(result.toString());
			}
		}
		return result.getData();
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Form:</b>Objectget</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Result<Void> get(String id) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("base/{id}", _uriVariables);

		return httpClientAdapter.<Result<Void>, Void> request("POST", _url, null, _1Type, true);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Form:</b>Objectget</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Future<?> get(String id, Callback<Result<Void>, Void> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("base/{id}", _uriVariables);

		return httpClientAdapter.requestAsync("POST", _url, null, _1Type, true, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/</b>
	 * <ul>
	 * <li><b>Form:</b>Usercreate</li>
	 * <li><b>Form:</b>Objectcreate</li>
	 * <li><b>Model:</b> User</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see User
	 * @see User

	 */
	public User createData(User user) {
		Result<User> result = create(user);
		if (!result.isSuccess()) {
			Exception ex = result.getException();
			if (ex != null) {
				throw new RuntimeException(ex.getMessage(), ex);
			} else {
				throw new RuntimeException(result.toString());
			}
		}
		return result.getData();
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/</b>
	 * <ul>
	 * <li><b>Form:</b>Usercreate</li>
	 * <li><b>Form:</b>Objectcreate</li>
	 * <li><b>Model:</b> User</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see User
	 * @see User

	 */
	public Result<User> create(User user) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("baseUrl/", _uriVariables);

		List<Entry<String, Object>> _form = user.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.<Result<User>, User> request("GET", _url, _form, _2Type, true);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/</b>
	 * <ul>
	 * <li><b>Form:</b>Usercreate</li>
	 * <li><b>Form:</b>Objectcreate</li>
	 * <li><b>Model:</b> User</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>
	 * @see User
	 * @see User

	 */
	public Future<?> create(User user, Callback<Result<User>, User> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("baseUrl/", _uriVariables);

		List<Entry<String, Object>> _form = user.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("GET", _url, _form, _2Type, true, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(
			Result.class,
			ApiUtils.type(java.util.ArrayList.class, TestObject.class,
					ApiUtils.type(java.util.ArrayList.class, User.class)));
	private static final ApiType _1Type = ApiUtils.type(Result.class, ApiUtils.type(Void.class));
	private static final ApiType _2Type = ApiUtils.type(Result.class, ApiUtils.type(User.class));
}