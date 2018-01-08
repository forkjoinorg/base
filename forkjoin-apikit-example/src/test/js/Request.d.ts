export interface RequestParamsType {
    method: string;
    url: string;
    pathVars: any;
    formObject: any;
}
interface Request extends EventTarget {
    init(params: RequestParamsType): any;
    start<T>(): Promise<T>;
    abort(): any;
}
export default Request;
