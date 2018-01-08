import HttpUtils from './HttpUtils';
import RequestGroup from './RequestGroup';

class AbstractApi {
    private requestGroup: RequestGroup;

    constructor() {
    }

    /**
     * 可以不设置
     */
    _init(httpGroup: RequestGroup) {
        this.requestGroup = httpGroup;
    }

    _request<T>(tag: string,
                method: string,
                url: string,
                pathVars: any,
                formObject: any): Promise<T> {
        return HttpUtils.request(
            tag,
            method,
            url,
            pathVars,
            formObject,
            this.requestGroup
        );
    }
}

export default AbstractApi;
