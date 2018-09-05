
import {AbstractApi} from 'apikit-core'

import {requestGroupImpi} from 'apikit-core'


class AccountApiController extends AbstractApi {

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
    login = () => {
        let _path = null;
        return super._request("test", "accountApiController", "POST", "account/login", _path, null);
    }


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
    testLogin = () => {
        let _path = null;
        return super._request("test", "accountApiController", "POST", "account/testLogin", _path, null);
    }


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
    testNotLogin = () => {
        let _path = null;
        return super._request("test", "accountApiController", "GET", "baseUrl/testNotLogin", _path, null);
    }

}

export { AccountApiController };
const accountApiController = new AccountApiController();
accountApiController._init(requestGroupImpi);
export default accountApiController;

