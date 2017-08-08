package org.forkjoin.apikit.example.client.api;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import org.forkjoin.apikit.client.*;

import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.client.model.User;

/**
 * @author  zuoge85 on 15/6/11.
 */
public class AccountApi {
	private HttpClientAdapter httpClientAdapter;

	public AccountApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 *  返回授权token
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public String loginData() {
		Result<String> result = login();
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
	 *  返回授权token
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Result<String> login() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/login", _uriVariables);

		return httpClientAdapter.<Result<String>, String> request("POST", _url, null, _0Type, false);
	}

	/**
	 *  返回授权token
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Future<?> login(Callback<Result<String>, String> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/login", _uriVariables);

		return httpClientAdapter.requestAsync("POST", _url, null, _0Type, false, callable);
	}

	/**
	 * 测试需要登录5
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/testLogin</b>
	 * <ul>
	 * <li><b>Form:</b>ExampleAccounttestLogin</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>

	 */
	public Void testLoginData() {
		Result<Void> result = testLogin();
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
	 * 测试需要登录5
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/testLogin</b>
	 * <ul>
	 * <li><b>Form:</b>ExampleAccounttestLogin</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>

	 */
	public Result<Void> testLogin() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/testLogin", _uriVariables);

		return httpClientAdapter.<Result<Void>, Void> request("POST", _url, null, _1Type, true);
	}

	/**
	 * 测试需要登录5
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/testLogin</b>
	 * <ul>
	 * <li><b>Form:</b>ExampleAccounttestLogin</li>
	 * <li><b>Model:</b> void</li>
	 * <li>需要登录</li>
	 * </ul>
	 * </div>

	 */
	public Future<?> testLogin(Callback<Result<Void>, Void> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("account/testLogin", _uriVariables);

		return httpClientAdapter.requestAsync("POST", _url, null, _1Type, true, callable);
	}

	/**
	 * 测试不需要登录
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/testNotLogin</b>
	 * <ul>
	 * <li><b>Model:</b> User</li>
	 * </ul>
	 * </div>
	 * @see User

	 */
	public User testNotLoginData() {
		Result<User> result = testNotLogin();
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
	 * 测试不需要登录
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/testNotLogin</b>
	 * <ul>
	 * <li><b>Model:</b> User</li>
	 * </ul>
	 * </div>
	 * @see User

	 */
	public Result<User> testNotLogin() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("baseUrl/testNotLogin", _uriVariables);

		return httpClientAdapter.<Result<User>, User> request("GET", _url, null, _2Type, false);
	}

	/**
	 * 测试不需要登录
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/testNotLogin</b>
	 * <ul>
	 * <li><b>Model:</b> User</li>
	 * </ul>
	 * </div>
	 * @see User

	 */
	public Future<?> testNotLogin(Callback<Result<User>, User> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("baseUrl/testNotLogin", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _2Type, false, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class, ApiUtils.type(String.class));
	private static final ApiType _1Type = ApiUtils.type(Result.class, ApiUtils.type(Void.class));
	private static final ApiType _2Type = ApiUtils.type(Result.class, ApiUtils.type(User.class));
}