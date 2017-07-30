'use strict';

import User from './../model/User'

import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class AccountApi extends AbstractApi {



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
    * <ul>
    * <li><b>Model:</b> String</li>
    * </ul>
    * </div>
    * @see String

    */
    login = ():Promise<String> => {
        var _path = null;
        return super._request(
                    "POST", "account/login", _path, null
                );
    };



   /**
    * 测试需要登录5
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/testLogin</b>
    * <ul>
    * <li><b>Model:</b> void</li>
    * <li>需要登录</li>
    * </ul>
    * </div>

    */
    testLogin = ():Promise<void> => {
        var _path = null;
        return super._request(
                    "POST", "account/testLogin", _path, null
                );
    };



   /**
    * 测试不需要登录
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>baseUrl/testNotLogin</b>
    * <ul>
    * <li><b>Model:</b> User</li>
    * </ul>
    * </div>
    * @see User

    */
    testNotLogin = ():Promise<User> => {
        var _path = null;
        return super._request(
                    "GET", "baseUrl/testNotLogin", _path, null
                );
    };

}
export default AccountApi;