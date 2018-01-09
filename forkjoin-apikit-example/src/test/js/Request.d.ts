export interface RequestParamsType {
    method: string;
    url: string;
    pathVars: any;
    formObject: any;
}
/**
 * @Event loadend 必须实现这个时间
 */
interface Request {
    init(params: RequestParamsType): any;
    start<T>(): Promise<T>;
    abort(): any;
    addEventListener(type: string, listener?: Function): void;
}
export default Request;
