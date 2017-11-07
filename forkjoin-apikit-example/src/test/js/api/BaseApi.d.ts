'use strict';

import TestForm from './../form/TestForm'

import TestObject from './../model/TestObject'

import User from './../model/User'

import AbstractApi from './../AbstractApi'


/**
 * 基本的测试api
 * @author   zuoge85 on 15/6/11.
*/
declare class BaseApi extends AbstractApi {



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base</b>
    * <ul>
    * <li><b>Form:</b>TestFormcreate</li>
    * <li><b>Model:</b> TestObject[]</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see TestObject[]
    * @see TestForm

    */
    create(testForm:TestForm):Promise<TestObject[]>;



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> string id</li>
    * <li><b>Model:</b> void</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see string

    */
    get(id:string):Promise<void>;



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/</b>
    * <ul>
    * <li><b>Form:</b>Usercreate</li>
    * <li><b>Model:</b> User</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see User
    * @see User

    */
    create(user:User):Promise<User>;

}
export { BaseApi };
declare const baseApi: BaseApi;
export default baseApi;