import {AccountApi} from './api/AccountApi';
import RequestGroup from './RequestGroup';
import {RequestGroupImpi} from './RequestGroupImpi';

class Apis {
  requestGroup: RequestGroup = new RequestGroupImpi();
  accountApi: AccountApi;

  constructor() {
    this.accountApi = new AccountApi();
    this.accountApi._init(this.requestGroup);
  }


  stopAll() {
    this.requestGroup.stopAll();
  }

  stop(tag: string) {
    this.requestGroup.stop(tag);
  }
}


export default Apis;
