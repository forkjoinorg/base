'use strict';


import AbstractApi from './../AbstractApi'


/**
 * 账户api
 * @author  zuoge85 on 15/6/11.
*/
class SysApi extends AbstractApi {



   /**
    *  返回授权token
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>status</b>
    * <ul>
    * <li><b>Model:</b> String</li>
    * </ul>
    * </div>
    * @see String

    */
    status = ():Promise<String> => {
        var _path = null;
        return super._request(
                    "GET", "status", _path, null
                );
    };



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
    login = ():Promise<Date> => {
        var _path = null;
        return super._request(
                    "GET", "now", _path, null
                );
    };

}
export default SysApi;