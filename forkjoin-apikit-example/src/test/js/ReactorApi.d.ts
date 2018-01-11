import TestForm from './form/TestForm'

import User from './model/User'

import AbstractApi from './AbstractApi'

import requestGroupImpi from './RequestGroupImpi'


/**
 * 测试一些复杂的情况
 * @author  zuoge85 on 15/6/11.
*/
declare class ReactorApi extends AbstractApi {



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testFlux</b>
    * <ul>
    * <li><b>Form:</b>TestFormtestFlux</li>
    * <li><b>Model:</b> User[]</li>
    * </ul>
    * </div>
    * @see User[]
    * @see TestForm

    */
    testFlux(testForm:TestForm):Promise<User[]>;



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>reactor/testMono</b>
    * <ul>
    * <li><b>Model:</b> Date</li>
    * </ul>
    * </div>
    * @see Date

    */
    testMono():Promise<Date>;

}
export { ReactorApi };
declare const reactorApi: ReactorApi;
export default reactorApi;