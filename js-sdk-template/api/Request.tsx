export interface RequestParamsType {
    method: string
    url: string
    pathVars: any
    formObject: any
}

/**
 * @Event loadend 必须实现这个时间
 */
interface Request {
    init(params: RequestParamsType);

    start<T>(): Promise<T>;

    abort();

    addEventListener(type: string, listener?: Function): void;
}

export default Request;
