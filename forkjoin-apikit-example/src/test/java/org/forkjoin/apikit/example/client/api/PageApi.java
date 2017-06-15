package org.forkjoin.apikit.example.client.api;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import org.forkjoin.apikit.client.*;

import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.apikit.example.client.model.TestObject;

/**
 * @author  zuoge85 on 15/6/11.
 */
public class PageApi {
	private HttpClientAdapter httpClientAdapter;

	public PageApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/page/{page}-{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> TestObject</li>
	 * </ul>
	 * </div>
	 * @see TestObject
	 * @see int
	 * @see int

	 */
	public List<TestObject> pageData(int page, int pageSize) {
		PageResult<TestObject> result = page(page, pageSize);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/page/{page}-{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> TestObject</li>
	 * </ul>
	 * </div>
	 * @see TestObject
	 * @see int
	 * @see int

	 */
	public PageResult<TestObject> page(int page, int pageSize) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("page/page/{page}-{pageSize}", _uriVariables);

		return httpClientAdapter.<PageResult<TestObject>, List<TestObject>> request("GET", _url, null, _0Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/page/{page}-{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> TestObject</li>
	 * </ul>
	 * </div>
	 * @see TestObject
	 * @see int
	 * @see int

	 */
	public Future<?> page(int page, int pageSize, Callback<PageResult<TestObject>, List<TestObject>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("page/page/{page}-{pageSize}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _0Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/pageString/{page}-{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see int
	 * @see int

	 */
	public List<String> pageStringData(int page, int pageSize) {
		PageResult<String> result = pageString(page, pageSize);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/pageString/{page}-{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see int
	 * @see int

	 */
	public PageResult<String> pageString(int page, int pageSize) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("page/pageString/{page}-{pageSize}", _uriVariables);

		return httpClientAdapter.<PageResult<String>, List<String>> request("GET", _url, null, _1Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/pageString/{page}-{pageSize}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> int page</li>
	 * <li><b>PathVariable:</b> int pageSize</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see int
	 * @see int

	 */
	public Future<?> pageString(int page, int pageSize, Callback<PageResult<String>, List<String>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("page", page);
		_uriVariables.put("pageSize", pageSize);
		String _url = ApiUtils.expandUriComponent("page/pageString/{page}-{pageSize}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _1Type, false, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(PageResult.class, ApiUtils.type(TestObject.class));
	private static final ApiType _1Type = ApiUtils.type(PageResult.class, ApiUtils.type(String.class));
}