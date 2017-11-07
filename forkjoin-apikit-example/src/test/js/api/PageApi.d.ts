'use strict';

import TestObject from './../model/TestObject'

import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
declare class PageApi extends AbstractApi {



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/page/{page}-{pageSize}</b>
    * <ul>
    * <li><b>PathVariable:</b> number page</li>
    * <li><b>PathVariable:</b> number pageSize</li>
    * <li><b>Model:</b> TestObject</li>
    * </ul>
    * </div>
    * @see TestObject
    * @see number
    * @see number

    */
    page(page:number, pageSize:number):Promise<TestObject>;



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>page/pageString/{page}-{pageSize}</b>
    * <ul>
    * <li><b>PathVariable:</b> number page</li>
    * <li><b>PathVariable:</b> number pageSize</li>
    * <li><b>Model:</b> string</li>
    * </ul>
    * </div>
    * @see string
    * @see number
    * @see number

    */
    pageString(page:number, pageSize:number):Promise<string>;

}
export { PageApi };
declare const pageApi: PageApi;
export default pageApi;