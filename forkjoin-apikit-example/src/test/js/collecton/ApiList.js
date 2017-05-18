'use strict';



/**
 * api的list集合,用于封装list数据
 * @author  zuoge85 on 15/8/11.
*/
class ApiList {

    list:Object[];
    constructor() {

    }

    formObject({list}):ApiList{
        this.list = list;
        return this;
    }

    static of({list}):ApiList{
        return new ApiList().formObject({list});
    }

    static form(list:Object[]):ApiList{
        return new ApiList().formObject({list});
    }
}

export default ApiList;