import HttpGroup from './HttpGroup';
declare class AbstractApi {
    private httpGroup?;
    constructor();
    /**
     * 可以不设置
     */
    _init(httpGroup: HttpGroup): void;
    _request<T>(tag: string, method: string, url: string, pathVars: any, formObject: any): Promise<T>;
}
export default AbstractApi;
