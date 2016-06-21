'use strict';

import BaseApi  from './api/BaseApi';
import TestApi  from './api/TestApi';

class Apis {
    baseApi:BaseApi;
    testApi:TestApi;
    _xhrArray:XMLHttpRequest[];

    constructor() {
        this._xhrArray = [];
        this.baseApi = new BaseApi();
        this.baseApi._initNet(this._xhrHandler.bind(this));
        this.testApi = new TestApi();
        this.testApi._initNet(this._xhrHandler.bind(this));
    }

    _xhrHandler(xhr:XMLHttpRequest) {
        this._xhrArray.push(xhr);
        xhr.loadend = ()=> {
            this._clearXhr(xhr);
        };
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