package org.forkjoin.apikit.example.client;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import org.forkjoin.apikit.client.*;

import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.client.form.TestForm;
import org.forkjoin.apikit.example.client.model.User;

/**
 * 测试一些复杂的情况
 * @author  zuoge85 on 15/6/11.
 */
public class ReactorApi {
	private HttpClientAdapter httpClientAdapter;

	public ReactorApi() {
	}

	public ReactorApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testFlux</b>
	 * <ul>
	 * <li><b>Form:</b>TestFormtestFlux</li>
	 * <li><b>Model:</b> java.util.ArrayList&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see java.util.ArrayList&lt;User&gt;
	 * @see TestForm

	 */
	public java.util.ArrayList<User> testFluxData(TestForm testForm) {
		Result<java.util.ArrayList<User>> result = testFlux(testForm);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testFlux</b>
	 * <ul>
	 * <li><b>Form:</b>TestFormtestFlux</li>
	 * <li><b>Model:</b> java.util.ArrayList&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see java.util.ArrayList&lt;User&gt;
	 * @see TestForm

	 */
	public Result<java.util.ArrayList<User>> testFlux(TestForm testForm) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("reactor/testFlux", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.<Result<java.util.ArrayList<User>>, java.util.ArrayList<User>> request("POST", _url,
				_form, _0Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testFlux</b>
	 * <ul>
	 * <li><b>Form:</b>TestFormtestFlux</li>
	 * <li><b>Model:</b> java.util.ArrayList&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see java.util.ArrayList&lt;User&gt;
	 * @see TestForm

	 */
	public Future<?> testFlux(TestForm testForm,
			Callback<Result<java.util.ArrayList<User>>, java.util.ArrayList<User>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("reactor/testFlux", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("POST", _url, _form, _0Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testMono</b>
	 * <ul>
	 * <li><b>Model:</b> Date</li>
	 * </ul>
	 * </div>
	 * @see Date

	 */
	public Date testMonoData() {
		Result<Date> result = testMono();
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testMono</b>
	 * <ul>
	 * <li><b>Model:</b> Date</li>
	 * </ul>
	 * </div>
	 * @see Date

	 */
	public Result<Date> testMono() {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("reactor/testMono", _uriVariables);

		return httpClientAdapter.<Result<Date>, Date> request("POST", _url, null, _1Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testMono</b>
	 * <ul>
	 * <li><b>Model:</b> Date</li>
	 * </ul>
	 * </div>
	 * @see Date

	 */
	public Future<?> testMono(Callback<Result<Date>, Date> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("reactor/testMono", _uriVariables);

		return httpClientAdapter.requestAsync("POST", _url, null, _1Type, false, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class,
			ApiUtils.type(java.util.ArrayList.class, User.class));
	private static final ApiType _1Type = ApiUtils.type(Result.class, ApiUtils.type(Date.class));
}