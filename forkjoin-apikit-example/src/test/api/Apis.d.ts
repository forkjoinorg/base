import { AccountApi } from './api/AccountApi';
import HttpGroup from './HttpGroup';
declare class Apis {
    httpGroup: HttpGroup;
    accountApi: AccountApi;
    constructor();
    stopAll(): void;
    stop(tag: string): void;
}
export default Apis;
