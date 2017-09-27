package org.forkjoin.apikit.example.client.api;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import org.forkjoin.apikit.client.*;

import org.forkjoin.apikit.core.Result;

/**
 * 账户api
 * @author  zuoge85 on 15/6/11.
 */
public class SysApi {
	private HttpClientAdapter httpClientAdapter;

	public SysApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 *  返回授权token
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>status</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public String statusData() {
		Result<String> result = status();
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>status</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Result<String> status() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("status", _uriVariables);

		return httpClientAdapter.<Result<String>, String> request("GET", _url, null, _0Type, false);
	}

	/**
	 *  返回授权token
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>status</b>
	 * <ul>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String

	 */
	public Future<?> status(Callback<Result<String>, String> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("status", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _0Type, false, callable);
	}

	/**
	 *  返回服务器当前时间
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>now</b>
	 * <ul>
	 * <li><b>Model:</b> Date</li>
	 * </ul>
	 * </div>
	 * @see Date

	 */
	public Date loginData() {
		Result<Date> result = login();
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
	 *  返回服务器当前时间
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>now</b>
	 * <ul>
	 * <li><b>Model:</b> Date</li>
	 * </ul>
	 * </div>
	 * @see Date

	 */
	public Result<Date> login() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("now", _uriVariables);

		return httpClientAdapter.<Result<Date>, Date> request("GET", _url, null, _1Type, false);
	}

	/**
	 *  返回服务器当前时间
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>now</b>
	 * <ul>
	 * <li><b>Model:</b> Date</li>
	 * </ul>
	 * </div>
	 * @see Date

	 */
	public Future<?> login(Callback<Result<Date>, Date> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("now", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _1Type, false, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class, ApiUtils.type(String.class));
	private static final ApiType _1Type = ApiUtils.type(Result.class, ApiUtils.type(Date.class));
}