import HttpRequest from './HttpRequest';
import HttpGroup from "./HttpGroup";
declare class HttpUtils {
    private static impl;
    static request<T>(tag: string, method: string, url: string, pathVars: Object, formObject: Object, handler?: HttpGroup): Promise<T>;
    static setImpoi(impi: HttpRequest): void;
}
export default HttpUtils;
