'use strict';

import TestForm from './../form/TestForm'

import TestObject from './../model/TestObject'

import TestObjectList from './../model/TestObjectList'

import User from './../model/User'

import AbstractApi from './../AbstractApi'


/**
 * 测试直接返回结果
 * @author  zuoge85 on 15/6/11.
*/
declare class TestNoResultApi extends AbstractApi {



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
    testObjectList(testForm:TestForm):Promise<TestObjectList>;



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
    create(testForm:TestForm):Promise<TestObject>;



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
    get(id:string):Promise<TestObject>;



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
    update(testForm:TestForm):Promise<TestObject>;



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
    patchUpdate(testForm:TestForm):Promise<TestObject>;



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
    delete(id:string):Promise<boolean>;



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
    deletes(id:string[]):Promise<number>;



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
    search(id:string, name:string):Promise<TestObject>;



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
    testString(name:string):Promise<string>;



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
    testString1(name:string, age:string):Promise<string>;



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
    page():Promise<TestObject>;

}
export { TestNoResultApi };
declare const testNoResultApi: TestNoResultApi;
export default testNoResultApi;