import HttpRequest from './HttpRequest'
import HttpGroup from "./HttpGroup";

class HttpUtils {
  private static impl: HttpRequest;

  static request<T>(tag: string,
                    method: string,
                    url: string,
                    pathVars: Object,
                    formObject: Object,
                    handler?: HttpGroup): Promise<T> {
    return HttpUtils.impl.request(tag,method, url, pathVars, formObject, handler);
  }

  static setImpoi(impi: HttpRequest) {
    HttpUtils.impl = impi;
  }
}

export default HttpUtils;
