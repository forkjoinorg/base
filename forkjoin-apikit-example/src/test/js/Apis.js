'use strict';

import { RequestGroupImpi } from './RequestGroupImpi';
import { ReactorApi } from './api/ReactorApi';


class Apis {
    constructor() {
        this.requestGroup = new RequestGroupImpi();
        this.reactorApi = new _ReactorApi.ReactorApi();
        this.reactorApi._init(this.httpGroup);
    }
    stopAll() {
        this.requestGroup.stopAll();
    }
    stop(tag) {
        this.requestGroup.stop(tag);
    }
}
export default Apis;