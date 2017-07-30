'use strict';

import TestObject from './../model/TestObject'

import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class PageApi extends AbstractApi {



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/page/{page}-{pageSize}</b>
    * <ul>
    * <li><b>PathVariable:</b> Number page</li>
    * <li><b>PathVariable:</b> Number pageSize</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see Number
    * @see Number

    */
    page = (page:Number, pageSize:Number):Promise<TestObject> => {
        var _path = {};
        _path["page"] = page;
        _path["pageSize"] = pageSize;
        return super._request(
                    "GET", "page/page/{page}-{pageSize}", _path, null
                );
    };



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/pageString/{page}-{pageSize}</b>
    * <ul>
    * <li><b>PathVariable:</b> Number page</li>
    * <li><b>PathVariable:</b> Number pageSize</li>
    * <li><b>Model:</b> String</li>
    * </ul>
    * </div>
    * @see String
    * @see Number
    * @see Number

    */
    pageString = (page:Number, pageSize:Number):Promise<String> => {
        var _path = {};
        _path["page"] = page;
        _path["pageSize"] = pageSize;
        return super._request(
                    "GET", "page/pageString/{page}-{pageSize}", _path, null
                );
    };

}
export default PageApi;