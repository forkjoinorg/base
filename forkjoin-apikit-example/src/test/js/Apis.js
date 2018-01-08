'use strict';

import { RequestGroupImpi } from './RequestGroupImpi';
import { BaseApi } from './api/BaseApi';
import { TestApi } from './api/TestApi';
import { TestNoResultApi } from './api/TestNoResultApi';
import { SysApi } from './api/SysApi';
import { AccountApi } from './api/AccountApi';
import { PageApi } from './api/PageApi';


class Apis {
    constructor() {
        this.requestGroup = new RequestGroupImpi();
        this.baseApi = new _BaseApi.BaseApi();
        this.baseApi._init(this.httpGroup);
        this.testApi = new _TestApi.TestApi();
        this.testApi._init(this.httpGroup);
        this.testNoResultApi = new _TestNoResultApi.TestNoResultApi();
        this.testNoResultApi._init(this.httpGroup);
        this.sysApi = new _SysApi.SysApi();
        this.sysApi._init(this.httpGroup);
        this.accountApi = new _AccountApi.AccountApi();
        this.accountApi._init(this.httpGroup);
        this.pageApi = new _PageApi.PageApi();
        this.pageApi._init(this.httpGroup);
    }
    stopAll() {
        this.requestGroup.stopAll();
    }
    stop(tag) {
        this.requestGroup.stop(tag);
    }
}
export default Apis;