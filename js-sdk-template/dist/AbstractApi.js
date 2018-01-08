import HttpUtils from './HttpUtils';
class AbstractApi {
    constructor() {
    }
    /**
     * 可以不设置
     */
    _init(httpGroup) {
        this.requestGroup = httpGroup;
    }
    _request(tag, method, url, pathVars, formObject) {
        return HttpUtils.request(tag, method, url, pathVars, formObject, this.requestGroup);
    }
}
export default AbstractApi;
