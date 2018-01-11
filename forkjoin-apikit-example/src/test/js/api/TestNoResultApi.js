
import AbstractApi from './../AbstractApi'

import requestGroupImpi from './../RequestGroupImpi'


class TestNoResultApi extends AbstractApi {

    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/testObjectList</b>
    * <ul>
    * <li><b>Form:</b>TestFormtestObjectList</li>
    * <li><b>Model:</b> TestObjectList</li>
    * </ul>
    * </div>
    * @see TestObjectList
    * @see TestForm

     */
    testObjectList = (testForm) => {
        let _path = null;
        return super._request("testNoResultApi", "POST", "noResult/testObjectList", _path, testForm);
    }


    /**
        * 添加
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/test</b>
    * <ul>
    * <li><b>Form:</b>TestFormcreate</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see TestForm

     */
    create = (testForm) => {
        let _path = null;
        return super._request("testNoResultApi", "POST", "noResult/test", _path, testForm);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/test/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> string id</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see string

     */
    get = (id) => {
        let _path = {};
        _path["id"] = id;
        return super._request("testNoResultApi", "GET", "noResult/test/{id}", _path, null);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/test</b>
    * <ul>
    * <li><b>Form:</b>TestFormupdate</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see TestForm

     */
    update = (testForm) => {
        let _path = null;
        return super._request("testNoResultApi", "PUT", "noResult/test", _path, testForm);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/test</b>
    * <ul>
    * <li><b>Form:</b>TestFormpatchUpdate</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see TestForm

     */
    patchUpdate = (testForm) => {
        let _path = null;
        return super._request("testNoResultApi", "PATCH", "noResult/test", _path, testForm);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/test/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> string id</li>
    * <li><b>Model:</b> boolean</li>
    * </ul>
    * </div>
    * @see boolean
    * @see string

     */
    delete = (id) => {
        let _path = {};
        _path["id"] = id;
        return super._request("testNoResultApi", "DELETE", "noResult/test/{id}", _path, null);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/tests/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> string[] id</li>
    * <li><b>Model:</b> number</li>
    * </ul>
    * </div>
    * @see number
    * @see string[]

     */
    deletes = (id) => {
        let _path = {};
        _path["id"] = id;
        return super._request("testNoResultApi", "DELETE", "noResult/tests/{id}", _path, null);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/search/{id}/{name}</b>
    * <ul>
    * <li><b>PathVariable:</b> string id</li>
    * <li><b>PathVariable:</b> string name</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see string
    * @see string

     */
    search = (id, name) => {
        let _path = {};
        _path["id"] = id;
        _path["name"] = name;
        return super._request("testNoResultApi", "GET", "noResult/search/{id}/{name}", _path, null);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/testString/{name}</b>
    * <ul>
    * <li><b>PathVariable:</b> string name</li>
    * <li><b>Model:</b> string</li>
    * </ul>
    * </div>
    * @see string
    * @see string

     */
    testString = (name) => {
        let _path = {};
        _path["name"] = name;
        return super._request("testNoResultApi", "GET", "noResult/testString/{name}", _path, null);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/testString1/{name}/{age}</b>
    * <ul>
    * <li><b>PathVariable:</b> string name</li>
    * <li><b>PathVariable:</b> string age</li>
    * <li><b>Model:</b> string</li>
    * </ul>
    * </div>
    * @see string
    * @see string
    * @see string

     */
    testString1 = (name, age) => {
        let _path = {};
        _path["name"] = name;
        _path["age"] = age;
        return super._request("testNoResultApi", "GET", "noResult/testString1/{name}/{age}", _path, null);
    }


    /**
        * 
     *
        * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/page</b>
    * <ul>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject

     */
    page = () => {
        let _path = null;
        return super._request("testNoResultApi", "GET", "noResult/page", _path, null);
    }

}

export { TestNoResultApi };
const testNoResultApi = new TestNoResultApi();
testNoResultApi._init(requestGroupImpi);
export default testNoResultApi;

