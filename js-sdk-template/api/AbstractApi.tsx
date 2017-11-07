import HttpUtils from './HttpUtils';
import HttpGroup from './HttpGroup';

class AbstractApi {
  private httpGroup?: HttpGroup;

  constructor() {
  }

  /**
   * 可以不设置
   */
  _init(httpGroup: HttpGroup) {
    this.httpGroup = httpGroup;
  }

  _request<T>(tag: string,
              method: string,
              url: string,
              pathVars: any,
              formObject: any): Promise<T> {
    return HttpUtils.request(
      tag,
      method,
      url,
      pathVars,
      formObject,
      this.httpGroup
    );
  }
}

export default AbstractApi;
