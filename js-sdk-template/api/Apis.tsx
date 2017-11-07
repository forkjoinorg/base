'use strict';

import {AccountApi} from './api/AccountApi';
import HttpGroup from './HttpGroup';
import {HttpGroupImpi} from './HttpGroupImpi';

class Apis {
  httpGroup: HttpGroup = new HttpGroupImpi();
  accountApi: AccountApi;

  constructor() {
    this.accountApi = new AccountApi();
    this.accountApi._init(this.httpGroup);
  }


  stopAll() {
    this.httpGroup.stopAll();
  }

  stop(tag: string) {
    this.httpGroup.stop(tag);
  }
}


export default Apis;
