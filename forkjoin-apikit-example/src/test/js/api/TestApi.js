'use strict';

import TestForm from './../form/TestForm'
import TestObject from './../model/TestObject'
import User from './../model/User'
import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class TestApi extends AbstractApi {



   /**
    * 添加
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/</b>
    * <ul>
    * <li><b>Form:</b>TestFormcreate</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see TestForm

    */
    create(testForm:TestForm):Promise {
        var _path = null;
        var _form = testForm;
        return super._request(
            "POST", "test/", _path, _form
        );

    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> String id</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see String

    */
    get(id:String):Promise {
        var _path = {};
        _path["id"] = id;
        var _form = null;
        return super._request(
            "GET", "test/{id}", _path, _form
        );

    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/</b>
    * <ul>
    * <li><b>Form:</b>TestFormupdate</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see TestForm

    */
    update(testForm:TestForm):Promise {
        var _path = null;
        var _form = testForm;
        return super._request(
            "PUT", "test/", _path, _form
        );

    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/</b>
    * <ul>
    * <li><b>Form:</b>TestFormpatchUpdate</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see TestForm

    */
    patchUpdate(testForm:TestForm):Promise {
        var _path = null;
        var _form = testForm;
        return super._request(
            "PATCH", "test/", _path, _form
        );

    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>test/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> String id</li>
    * <li><b>Model:</b> Boolean</li>
    * </ul>
    * </div>
    * @see Boolean
    * @see String

    */
    delete(id:String):Promise {
        var _path = {};
        _path["id"] = id;
        var _form = null;
        return super._request(
            "DELETE", "test/{id}", _path, _form
        );

    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>tests/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> List id</li>
    * <li><b>Model:</b> Number</li>
    * </ul>
    * </div>
    * @see Number
    * @see List

    */
    deletes(id:List):Promise {
        var _path = {};
        _path["id"] = id;
        var _form = null;
        return super._request(
            "DELETE", "tests/{id}", _path, _form
        );

    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>search/{id}/{name}</b>
    * <ul>
    * <li><b>PathVariable:</b> String id</li>
    * <li><b>PathVariable:</b> String name</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see String
    * @see String

    */
    search(id:String, name:String):Promise {
        var _path = {};
        _path["id"] = id;
        _path["name"] = name;
        var _form = null;
        return super._request(
            "GET", "search/{id}/{name}", _path, _form
        );

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
    testString(name:String):Promise {
        var _path = {};
        _path["name"] = name;
        var _form = null;
        return super._request(
            "GET", "testString/{name}", _path, _form
        );

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
    testString1(name:String, age:String):Promise {
        var _path = {};
        _path["name"] = name;
        _path["age"] = age;
        var _form = null;
        return super._request(
            "GET", "testString1/{name}/{age}", _path, _form
        );

    }

}
export default TestApi;