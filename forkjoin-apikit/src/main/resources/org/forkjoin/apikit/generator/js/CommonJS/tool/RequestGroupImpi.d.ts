import RequestGroup from "./RequestGroup";
import Request from "./Request";
/**
 * 一组Http 请求，用于管理http请求，进行批量清理等操作
 * 比如页面关闭后相关请求的停止。
 */
declare class RequestGroupImpi implements RequestGroup {
    private requestMap;
    private _isStop;
    constructor();
    add(tag: string, request: Request): void;
    _clear(tag: string, request: Request): void;
    /**
     * 停止已经发起的请求，不会阻止之后发生的任何请求
     *
     * @param {string} tag
     */
    stop(tag: string): void;
    /**
     * 停止全部请求，且会阻止其后发起的任何请求
     */
    stopAll(): void;
}
export { RequestGroupImpi };
declare const _default: RequestGroupImpi;
export default _default;
