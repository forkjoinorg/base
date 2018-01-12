
import AbstractApi from './../AbstractApi'

import requestGroupImpi from './../RequestGroupImpi'


class SysApiController extends AbstractApi {

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
    status = () => {
        let _path = null;
        return super._request("sysApiController", "GET", "status", _path, null);
    }


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
    login = () => {
        let _path = null;
        return super._request("sysApiController", "GET", "now", _path, null);
    }

}

export { SysApiController };
const sysApiController = new SysApiController();
sysApiController._init(requestGroupImpi);
export default sysApiController;

