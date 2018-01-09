
import ReactorApi  from './api/ReactorApi';
import RequestGroup from './RequestGroup';

declare class Apis {
    requestGroup: RequestGroup;
    reactorApi:ReactorApi;
    constructor();
    stopAll(): void;
    stop(tag: string): void;
}
export default Apis;