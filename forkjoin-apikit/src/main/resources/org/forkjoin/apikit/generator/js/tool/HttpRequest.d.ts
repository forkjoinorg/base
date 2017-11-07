import HttpGroup from "./HttpGroup";
interface HttpRequest {
    request<T>(tag: string, method: string, url: string, pathVars: Object, formObject: Object, handler?: HttpGroup): Promise<T>;
}
export default HttpRequest;
