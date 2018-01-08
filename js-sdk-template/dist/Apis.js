import { AccountApi } from './api/AccountApi';
import { RequestGroupImpi } from './RequestGroupImpi';
class Apis {
    constructor() {
        this.requestGroup = new RequestGroupImpi();
        this.accountApi = new AccountApi();
        this.accountApi._init(this.requestGroup);
    }
    stopAll() {
        this.requestGroup.stopAll();
    }
    stop(tag) {
        this.requestGroup.stop(tag);
    }
}
export default Apis;
