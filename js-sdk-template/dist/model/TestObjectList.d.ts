import User from './User';
/**
 * @author  zuoge85 on 15/6/17.
*/
interface TestObjectList {
    id?: string;
    /**
     * 签名
     * @see java.lang
java.lang
     */
    booleanValue?: boolean;
    intValue?: number;
    longValue?: number;
    floatValue?: number;
    doubleValue?: number;
    stringValue?: string;
    bytesValue?: number[];
    regDate?: Date;
    booleanValueArray?: boolean[];
    intValueArray?: number[];
    longValueArray?: number[];
    floatValueArray?: number[];
    doubleValueArray?: number[];
    stringValueArray?: string[];
    regDateArray?: Date[];
    user?: User;
    users?: User[];
    generics?: Object[];
    genericObjs?: TestObjectList[];
    genericUsers?: TestObjectList[];
    genericObj?: TestObjectList;
    generic?: Object;
}
export default TestObjectList;
