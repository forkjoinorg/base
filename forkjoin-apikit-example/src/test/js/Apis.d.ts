
import BaseApi  from './api/BaseApi';
import TestApi  from './api/TestApi';
import TestNoResultApi  from './api/TestNoResultApi';
import SysApi  from './api/SysApi';
import AccountApi  from './api/AccountApi';
import PageApi  from './api/PageApi';
import HttpGroup from './HttpGroup';

declare class Apis {
    httpGroup: HttpGroup;
    baseApi:BaseApi;
    testApi:TestApi;
    testNoResultApi:TestNoResultApi;
    sysApi:SysApi;
    accountApi:AccountApi;
    pageApi:PageApi;
    constructor();
    stopAll(): void;
    stop(tag: string): void;
}
export default Apis;