import RequestGroup from "./RequestGroup"
import Request from "./Request"

/**
 * 一组Http 请求，用于管理http请求，进行批量清理等操作
 * 比如页面关闭后相关请求的停止。
 */
class RequestGroupImpi implements RequestGroup {
    private requestMap: { [index: string]: Request[] } = {};
    private _isStop = true;

    constructor() {
        this._isStop = false;
    }

    add(tag: string, request: Request) {
        if (this._isStop) {
            request.abort();
        } else {
            let requestArray = this.requestMap[tag];
            if (!requestArray) {
                requestArray = [];
                this.requestMap[tag] = requestArray;
            }
            requestArray.push(request);
            request.addEventListener("loadend", () => {
                this._clear(tag, request);
            })
        }
    }

    _clear(tag: string, request: Request) {
        let requestArray = this.requestMap[tag];
        if (requestArray) {
            let index = requestArray.indexOf(request);
            if (index > -1) {
                requestArray.splice(index, 1);
            }
        }
    }

    /**
     * 停止已经发起的请求，不会阻止之后发生的任何请求
     *
     * @param {string} tag
     */
    stop(tag: string) {
        let requestArray = this.requestMap[tag];
        if (requestArray) {
            requestArray.forEach((item) => {
                item.abort();
            });
            requestArray.length = 0;
        }
    }

    /**
     * 停止全部请求，且会阻止其后发起的任何请求
     */
    stopAll() {
        for (let tag in this.requestMap) {
            let requestArray = this.requestMap[tag];
            if (requestArray) {
                requestArray.forEach((item) => {
                    item.abort();
                })
            }
        }
        this.requestMap = {};
        this._isStop = true;
    }
}


export {RequestGroupImpi};
export default new RequestGroupImpi();
