import Request from './Request';
/**
 * 一组Http 请求，用于管理http请求，进行批量清理等操作
 * 比如页面关闭后相关请求的停止。
 */
interface RequestGroup {
    add(tag: string, request: Request): any;
    stop(tag: string): any;
    stopAll(): any;
}
export default RequestGroup;
