import { AccountApi } from './api/AccountApi';
import RequestGroup from './RequestGroup';
declare class Apis {
    requestGroup: RequestGroup;
    accountApi: AccountApi;
    constructor();
    stopAll(): void;
    stop(tag: string): void;
}
export default Apis;
