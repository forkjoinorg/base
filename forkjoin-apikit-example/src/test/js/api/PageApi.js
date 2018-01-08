
import AbstractApi from './../AbstractApi'

import requestGroupImpi from './../RequestGroupImpi'


class PageApi extends AbstractApi {

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
    page(page, pageSize){
        let _path = {};
        _path["page"] = page;
        _path["pageSize"] = pageSize;
        return super._request("pageApi", "GET", "page/page/{page}-{pageSize}", _path, null);
    }


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
    pageString(page, pageSize){
        let _path = {};
        _path["page"] = page;
        _path["pageSize"] = pageSize;
        return super._request("pageApi", "GET", "page/pageString/{page}-{pageSize}", _path, null);
    }

}

export { PageApi };
const pageApi = new PageApi();
pageApi._init(requestGroupImpi);
export default pageApi;

