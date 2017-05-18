'use strict';

import BaseApi  from './api/BaseApi';
import TestApi  from './api/TestApi';
import ApiUtils from "./ApiUtils";

class Apis {
    baseApi:BaseApi;
    testApi:TestApi;
    _xhrArray:XMLHttpRequest[];
    _isStop = true;

    static init(apiUrl:String, apiVersion:String){
        ApiUtils.apiUrl = apiUrl;
        ApiUtils.apiVersion = apiVersion;
    }

    static setToken(token:String){
        ApiUtils.setToken(token);
    }

    constructor() {
        this._xhrArray = [];
        let _xhrHandler = this._xhrHandler.bind(this);
        this.baseApi = new BaseApi();
        this.baseApi._initNet(_xhrHandler);
        this.testApi = new TestApi();
        this.testApi._initNet(_xhrHandler);
        this._isStop = false;
    }

    _xhrHandler(xhr:XMLHttpRequest) {
        if(this._isStop){
            xhr.abort();
        }else{
            this._xhrArray.push(xhr);
            xhr.loadend = ()=> {
                this._clearXhr(xhr);
            };
        }
    }

    _clearXhr(xhr:XMLHttpRequest) {
        var index = this._xhrArray.indexOf(xhr);
        if (index > -1) {
            this._xhrArray.splice(index, 1);
        }
    }

    stopAll() {
        for (var i = 0; i < this._xhrArray.length; i++) {
            this._xhrArray[i].abort();
        }
        this._xhrArray.length = 0;
    }
}


export default Apis;