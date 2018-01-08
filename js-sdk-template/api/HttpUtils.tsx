import Request from './Request'
import RequestGroup from "./RequestGroup";

class HttpUtils {
    private static factory: () => Request;

    static request<T>(tag: string,
                      method: string,
                      url: string,
                      pathVars: any,
                      formObject: any,
                      requestGroup: RequestGroup): Promise<T> {

        let request = HttpUtils.factory();
        request.init({method, url, pathVars, formObject});
        requestGroup.add(tag, request);
        return request.start<T>();
    }

    static setFactory(factory: () => Request) {
        HttpUtils.factory = factory;
    }
}

export default HttpUtils;
