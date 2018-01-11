
import AbstractApi from './AbstractApi'

import requestGroupImpi from './RequestGroupImpi'


class ReactorApi extends AbstractApi {

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
    testFlux = (testForm) => {
        let _path = null;
        return super._request("reactorApi", "POST", "reactor/testFlux", _path, testForm);
    }


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
    testMono = () => {
        let _path = null;
        return super._request("reactorApi", "POST", "reactor/testMono", _path, null);
    }

}

export { ReactorApi };
const reactorApi = new ReactorApi();
reactorApi._init(requestGroupImpi);
export default reactorApi;

