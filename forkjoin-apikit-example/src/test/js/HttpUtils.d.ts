import Request from './Request';
import RequestGroup from "./RequestGroup";
declare class HttpUtils {
    private static factory;
    static request<T>(tag: string, method: string, url: string, pathVars: any, formObject: any, requestGroup: RequestGroup): Promise<T>;
    static setFactory(factory: () => Request): void;
}
export default HttpUtils;
