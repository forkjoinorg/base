
import AbstractApi from './../AbstractApi'

import requestGroupImpi from './../RequestGroupImpi'


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
    create = (testForm) => {
        let _path = null;
        return super._request("baseApi", "POST", "base", _path, testForm);
    }


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
    get = (id) => {
        let _path = {};
        _path["id"] = id;
        return super._request("baseApi", "POST", "base/{id}", _path, null);
    }


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
    create = (user) => {
        let _path = null;
        return super._request("baseApi", "GET", "baseUrl/", _path, user);
    }

}

export { BaseApi };
const baseApi = new BaseApi();
baseApi._init(requestGroupImpi);
export default baseApi;

