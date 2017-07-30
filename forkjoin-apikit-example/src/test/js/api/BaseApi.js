'use strict';

import TestForm from './../form/TestForm'

import TestObject from './../model/TestObject'

import User from './../model/User'

import AbstractApi from './../AbstractApi'


/**
 * @author   zuoge85 on 15/6/11.
*/
class BaseApi extends AbstractApi {



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
    create = (testForm:TestForm):Promise<TestObject[]> => {
        var _path = null;
        return super._request(
                    "POST", "base", _path, testForm
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>base/{id}</b>
    * <ul>
    * <li><b>PathVariable:</b> String id</li>
    * <li><b>Model:</b> void</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see String

    */
    get = (id:String):Promise<void> => {
        var _path = {};
        _path["id"] = id;
        return super._request(
                    "POST", "base/{id}", _path, null
                );
    };



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
    create = (user:User):Promise<User> => {
        var _path = null;
        return super._request(
                    "GET", "baseUrl/", _path, user
                );
    };

}
export default BaseApi;