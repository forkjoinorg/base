package org.forkjoin.apikit.example.client.api;

import org.forkjoin.apikit.example.client.model.TestObjectList;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.forkjoin.apikit.core.*;
import org.forkjoin.apikit.example.client.form.TestForm;
import org.forkjoin.apikit.example.client.model.TestObject;
import org.forkjoin.apikit.example.client.model.User;
import java.util.List;

import java.util.*;
import java.util.concurrent.Future;
import java.util.Map.Entry;

import org.forkjoin.apikit.client.*;

/**
 * @author   zuoge85 on 15/6/11.
 */
public class TestApi {
	private HttpClientAdapter httpClientAdapter;

	public TestApi(HttpClientAdapter httpClientAdapter) {
		this.httpClientAdapter = httpClientAdapter;
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testObjectList</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;testObjectList</li>
	 * <li><b>Model:</b> TestObjectList&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObjectList&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public TestObjectList<User> testObjectListData(TestForm<User> testForm) {
		Result<TestObjectList<User>> result = testObjectList(testForm);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testObjectList</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;testObjectList</li>
	 * <li><b>Model:</b> TestObjectList&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObjectList&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Result<TestObjectList<User>> testObjectList(TestForm<User> testForm) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("testObjectList", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.request("POST", _url, _form, _0Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testObjectList</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;testObjectList</li>
	 * <li><b>Model:</b> TestObjectList&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObjectList&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Future<?> testObjectList(TestForm<User> testForm, Callback<TestObjectList<User>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("testObjectList", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("POST", _url, _form, _0Type, false, callable);
	}

	/**
	 * 添加
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;create</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public TestObject<User> createData(TestForm<User> testForm) {
		Result<TestObject<User>> result = create(testForm);
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
	 * 添加
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;create</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Result<TestObject<User>> create(TestForm<User> testForm) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("test", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.request("POST", _url, _form, _1Type, false);
	}

	/**
	 * 添加
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;create</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Future<?> create(TestForm<User> testForm, Callback<TestObject<User>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("test", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("POST", _url, _form, _1Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see String

	 */
	public TestObject<User> getData(String id) {
		Result<TestObject<User>> result = get(id);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see String

	 */
	public Result<TestObject<User>> get(String id) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("test/{id}", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _2Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see String

	 */
	public Future<?> get(String id, Callback<TestObject<User>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("test/{id}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _2Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;update</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public TestObject<User> updateData(TestForm<User> testForm) {
		Result<TestObject<User>> result = update(testForm);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;update</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Result<TestObject<User>> update(TestForm<User> testForm) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("test", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.request("PUT", _url, _form, _3Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;update</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Future<?> update(TestForm<User> testForm, Callback<TestObject<User>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("test", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("PUT", _url, _form, _3Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;patchUpdate</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public TestObject<User> patchUpdateData(TestForm<User> testForm) {
		Result<TestObject<User>> result = patchUpdate(testForm);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;patchUpdate</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Result<TestObject<User>> patchUpdate(TestForm<User> testForm) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("test", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.request("PATCH", _url, _form, _4Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test</b>
	 * <ul>
	 * <li><b>Form:</b>TestForm&lt;User&gt;patchUpdate</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see TestForm&lt;User&gt;

	 */
	public Future<?> patchUpdate(TestForm<User> testForm, Callback<TestObject<User>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		String _url = ApiUtils.expandUriComponent("test", _uriVariables);

		List<Entry<String, Object>> _form = testForm.encode("", new ArrayList<Entry<String, Object>>());
		return httpClientAdapter.requestAsync("PATCH", _url, _form, _4Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Model:</b> boolean</li>
	 * </ul>
	 * </div>
	 * @see boolean
	 * @see String

	 */
	public Boolean deleteData(String id) {
		Result<Boolean> result = delete(id);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Model:</b> boolean</li>
	 * </ul>
	 * </div>
	 * @see boolean
	 * @see String

	 */
	public Result<Boolean> delete(String id) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("test/{id}", _uriVariables);

		return httpClientAdapter.request("DELETE", _url, null, _5Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>Model:</b> boolean</li>
	 * </ul>
	 * </div>
	 * @see boolean
	 * @see String

	 */
	public Future<?> delete(String id, Callback<Boolean> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("test/{id}", _uriVariables);

		return httpClientAdapter.requestAsync("DELETE", _url, null, _5Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>tests/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> List&lt;String&gt; id</li>
	 * <li><b>Model:</b> int</li>
	 * </ul>
	 * </div>
	 * @see int
	 * @see List&lt;String&gt;

	 */
	public Integer deletesData(List<String> id) {
		Result<Integer> result = deletes(id);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>tests/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> List&lt;String&gt; id</li>
	 * <li><b>Model:</b> int</li>
	 * </ul>
	 * </div>
	 * @see int
	 * @see List&lt;String&gt;

	 */
	public Result<Integer> deletes(List<String> id) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("tests/{id}", _uriVariables);

		return httpClientAdapter.request("DELETE", _url, null, _6Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>tests/{id}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> List&lt;String&gt; id</li>
	 * <li><b>Model:</b> int</li>
	 * </ul>
	 * </div>
	 * @see int
	 * @see List&lt;String&gt;

	 */
	public Future<?> deletes(List<String> id, Callback<Integer> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		String _url = ApiUtils.expandUriComponent("tests/{id}", _uriVariables);

		return httpClientAdapter.requestAsync("DELETE", _url, null, _6Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>search/{id}/{name}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see String
	 * @see String

	 */
	public TestObject<User> searchData(String id, String name) {
		Result<TestObject<User>> result = search(id, name);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>search/{id}/{name}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see String
	 * @see String

	 */
	public Result<TestObject<User>> search(String id, String name) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		_uriVariables.put("name", name);
		String _url = ApiUtils.expandUriComponent("search/{id}/{name}", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _7Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>search/{id}/{name}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String id</li>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>Model:</b> TestObject&lt;User&gt;</li>
	 * </ul>
	 * </div>
	 * @see TestObject&lt;User&gt;
	 * @see String
	 * @see String

	 */
	public Future<?> search(String id, String name, Callback<TestObject<User>> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("id", id);
		_uriVariables.put("name", name);
		String _url = ApiUtils.expandUriComponent("search/{id}/{name}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _7Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testString/{name}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see String

	 */
	public String testStringData(String name) {
		Result<String> result = testString(name);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testString/{name}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see String

	 */
	public Result<String> testString(String name) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("name", name);
		String _url = ApiUtils.expandUriComponent("testString/{name}", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _8Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testString/{name}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see String

	 */
	public Future<?> testString(String name, Callback<String> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("name", name);
		String _url = ApiUtils.expandUriComponent("testString/{name}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _8Type, false, callable);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testString1/{name}/{age}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>PathVariable:</b> String age</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see String
	 * @see String

	 */
	public String testString1Data(String name, String age) {
		Result<String> result = testString1(name, age);
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
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testString1/{name}/{age}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>PathVariable:</b> String age</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see String
	 * @see String

	 */
	public Result<String> testString1(String name, String age) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("name", name);
		_uriVariables.put("age", age);
		String _url = ApiUtils.expandUriComponent("testString1/{name}/{age}", _uriVariables);

		return httpClientAdapter.request("GET", _url, null, _9Type, false);
	}

	/**
	 * 
	 *
	 * <div class='http-info'>http 说明：<b>Api Url:</b> <b>testString1/{name}/{age}</b>
	 * <ul>
	 * <li><b>PathVariable:</b> String name</li>
	 * <li><b>PathVariable:</b> String age</li>
	 * <li><b>Model:</b> String</li>
	 * </ul>
	 * </div>
	 * @see String
	 * @see String
	 * @see String

	 */
	public Future<?> testString1(String name, String age, Callback<String> callable) {
		Map<String, Object> _uriVariables = new HashMap<>();
		_uriVariables.put("name", name);
		_uriVariables.put("age", age);
		String _url = ApiUtils.expandUriComponent("testString1/{name}/{age}", _uriVariables);

		return httpClientAdapter.requestAsync("GET", _url, null, _9Type, false, callable);
	}

	private static final ApiType _0Type = ApiUtils.type(Result.class,
			ApiUtils.type(TestObjectList.class, ApiUtils.type(User.class)));
	private static final ApiType _1Type = ApiUtils.type(Result.class,
			ApiUtils.type(TestObject.class, ApiUtils.type(User.class)));
	private static final ApiType _2Type = ApiUtils.type(Result.class,
			ApiUtils.type(TestObject.class, ApiUtils.type(User.class)));
	private static final ApiType _3Type = ApiUtils.type(Result.class,
			ApiUtils.type(TestObject.class, ApiUtils.type(User.class)));
	private static final ApiType _4Type = ApiUtils.type(Result.class,
			ApiUtils.type(TestObject.class, ApiUtils.type(User.class)));
	private static final ApiType _5Type = ApiUtils.type(Result.class, ApiUtils.type(Boolean.class));
	private static final ApiType _6Type = ApiUtils.type(Result.class, ApiUtils.type(Integer.class));
	private static final ApiType _7Type = ApiUtils.type(Result.class,
			ApiUtils.type(TestObject.class, ApiUtils.type(User.class)));
	private static final ApiType _8Type = ApiUtils.type(Result.class, ApiUtils.type(String.class));
	private static final ApiType _9Type = ApiUtils.type(Result.class, ApiUtils.type(String.class));
}