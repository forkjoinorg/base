class HttpUtils {
    static request(tag, method, url, pathVars, formObject, requestGroup) {
        let request = HttpUtils.factory();
        request.init({ method, url, pathVars, formObject });
        requestGroup.add(tag, request);
        return request.start();
    }
    static setFactory(factory) {
        HttpUtils.factory = factory;
    }
}
export default HttpUtils;
