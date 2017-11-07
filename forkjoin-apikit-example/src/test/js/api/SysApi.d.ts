'use strict';


import AbstractApi from './../AbstractApi'


/**
 * 账户api
 * @author  zuoge85 on 15/6/11.
*/
declare class SysApi extends AbstractApi {



   /**
    *  返回授权token
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>status</b>
    * <ul>
    * <li><b>Model:</b> string</li>
    * </ul>
    * </div>
    * @see string

    */
    status():Promise<string>;



   /**
    *  返回服务器当前时间
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>now</b>
    * <ul>
    * <li><b>Model:</b> Date</li>
    * </ul>
    * </div>
    * @see Date

    */
    login():Promise<Date>;

}
export { SysApi };
declare const sysApi: SysApi;
export default sysApi;