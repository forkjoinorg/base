'use strict';

import AccountApi  from './api/AccountApi';
import BaseApi  from './api/BaseApi';
import PageApi  from './api/PageApi';
import TestApi  from './api/TestApi';
import TestNoResultApi  from './api/TestNoResultApi';
import ApiUtils from "./ApiUtils";

class Apis {
    accountApi:AccountApi;
    baseApi:BaseApi;
    pageApi:PageApi;
    testApi:TestApi;
    testNoResultApi:TestNoResultApi;
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
        this.accountApi = new AccountApi();
        this.accountApi._initNet(_xhrHandler);
        this.baseApi = new BaseApi();
        this.baseApi._initNet(_xhrHandler);
        this.pageApi = new PageApi();
        this.pageApi._initNet(_xhrHandler);
        this.testApi = new TestApi();
        this.testApi._initNet(_xhrHandler);
        this.testNoResultApi = new TestNoResultApi();
        this.testNoResultApi._initNet(_xhrHandler);
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