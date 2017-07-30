'use strict';

import TestForm from './../form/TestForm'

import TestObject from './../model/TestObject'

import TestObjectList from './../model/TestObjectList'

import User from './../model/User'

import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
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
    testObjectList = (testForm:TestForm):Promise<TestObjectList> => {
        var _path = null;
        return super._request(
                    "POST", "noResult/testObjectList", _path, testForm
                );
    };



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
    create = (testForm:TestForm):Promise<TestObject> => {
        var _path = null;
        return super._request(
                    "POST", "noResult/test", _path, testForm
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/test/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> String id</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see String

    */
    get = (id:String):Promise<TestObject> => {
        var _path = {};
        _path["id"] = id;
        return super._request(
                    "GET", "noResult/test/{id}", _path, null
                );
    };



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
    update = (testForm:TestForm):Promise<TestObject> => {
        var _path = null;
        return super._request(
                    "PUT", "noResult/test", _path, testForm
                );
    };



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
    patchUpdate = (testForm:TestForm):Promise<TestObject> => {
        var _path = null;
        return super._request(
                    "PATCH", "noResult/test", _path, testForm
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/test/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> String id</li>
    * <li><b>Model:</b> Boolean</li>
    * </ul>
    * </div>
    * @see Boolean
    * @see String

    */
    delete = (id:String):Promise<Boolean> => {
        var _path = {};
        _path["id"] = id;
        return super._request(
                    "DELETE", "noResult/test/{id}", _path, null
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/tests/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> List id</li>
    * <li><b>Model:</b> Number</li>
    * </ul>
    * </div>
    * @see Number
    * @see List

    */
    deletes = (id:List):Promise<Number> => {
        var _path = {};
        _path["id"] = id;
        return super._request(
                    "DELETE", "noResult/tests/{id}", _path, null
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/search/{id}/{name}</b>
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
    search = (id:String, name:String):Promise<TestObject> => {
        var _path = {};
        _path["id"] = id;
        _path["name"] = name;
        return super._request(
                    "GET", "noResult/search/{id}/{name}", _path, null
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/testString/{name}</b>
    * <ul>
    * <li><b>PathVariable:</b> String name</li>
    * <li><b>Model:</b> String</li>
    * </ul>
    * </div>
    * @see String
    * @see String

    */
    testString = (name:String):Promise<String> => {
        var _path = {};
        _path["name"] = name;
        return super._request(
                    "GET", "noResult/testString/{name}", _path, null
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>noResult/testString1/{name}/{age}</b>
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
    testString1 = (name:String, age:String):Promise<String> => {
        var _path = {};
        _path["name"] = name;
        _path["age"] = age;
        return super._request(
                    "GET", "noResult/testString1/{name}/{age}", _path, null
                );
    };



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
    page = ():Promise<TestObject> => {
        var _path = null;
        return super._request(
                    "GET", "noResult/page", _path, null
                );
    };

}
export default TestNoResultApi;