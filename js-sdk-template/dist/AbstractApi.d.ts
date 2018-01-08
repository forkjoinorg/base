import RequestGroup from './RequestGroup';
declare class AbstractApi {
    private requestGroup;
    constructor();
    /**
     * 可以不设置
     */
    _init(httpGroup: RequestGroup): void;
    _request<T>(tag: string, method: string, url: string, pathVars: any, formObject: any): Promise<T>;
}
export default AbstractApi;
