/**
 * 一组Http 请求，用于管理http请求，进行批量清理等操作
 * 比如页面关闭后相关请求的停止。
 */
interface HttpGroup {
  add(tag: string, request: XMLHttpRequest);

  stop(tag: string);

  stopAll();
}


export default HttpGroup;
