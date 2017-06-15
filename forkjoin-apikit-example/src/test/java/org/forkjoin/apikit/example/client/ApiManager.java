package org.forkjoin.apikit.example.client;

import org.forkjoin.apikit.client.HttpClientAdapter;

import org.forkjoin.apikit.example.client.api.AccountApi;
import org.forkjoin.apikit.example.client.api.BaseApi;
import org.forkjoin.apikit.example.client.api.PageApi;
import org.forkjoin.apikit.example.client.api.TestApi;
import org.forkjoin.apikit.example.client.api.TestNoResultApi;

/**
 * api 管理器，单例，使用之前需要初始化
 * @author zuoge85 on 15/6/15.
 */
public class ApiManager {
	private static ApiManager instance;

	/**
	 * 初始化
	 */
	public static void init(HttpClientAdapter httpClientAdapter) {
		instance = new ApiManager(httpClientAdapter);
	}

	public static ApiManager getInstance() {
		return instance;
	}

	private HttpClientAdapter httpClientAdapter;

	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final AccountApi accountApi;
	/**
	 * @author   zuoge85 on 15/6/11.
	 */
	public final BaseApi baseApi;
	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final PageApi pageApi;
	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final TestApi testApi;
	/**
	 * @author  zuoge85 on 15/6/11.
	 */
	public final TestNoResultApi testNoResultApi;

	public ApiManager(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;

		accountApi = new AccountApi(httpClientAdapter);
		baseApi = new BaseApi(httpClientAdapter);
		pageApi = new PageApi(httpClientAdapter);
		testApi = new TestApi(httpClientAdapter);
		testNoResultApi = new TestNoResultApi(httpClientAdapter);
	}
}
