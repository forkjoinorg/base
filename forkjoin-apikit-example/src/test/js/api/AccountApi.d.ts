'use strict';

import User from './../model/User'

import AbstractApi from './../AbstractApi'


/**
 * 账户api
 * @author  zuoge85 on 15/6/11.
*/
declare class AccountApi extends AbstractApi {



   /**
    *  返回授权token
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
    * <ul>
    * <li><b>Model:</b> string</li>
    * </ul>
    * </div>
    * @see string

    */
    login():Promise<string>;



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
    testLogin():Promise<void>;



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
    testNotLogin():Promise<User>;

}
export { AccountApi };
declare const accountApi: AccountApi;
export default accountApi;