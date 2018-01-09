package org.forkjoin.apikit.example.client;

import org.forkjoin.apikit.client.HttpClientAdapter;

import org.forkjoin.apikit.example.client.api.ReactorApi;

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
	 * 测试一些复杂的情况
	 * @author  zuoge85 on 15/6/11.
	 */
	public final ReactorApi reactorApi;

	public ApiManager(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;

		reactorApi = new ReactorApi(httpClientAdapter);
	}
}
