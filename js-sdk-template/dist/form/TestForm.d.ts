import User from '../model/User';
/**
 * 好吧，测试表单
<p>
<p>
<h1>好呀</h1>
 * @author  zuoge85 on 15/4/18.
 * @see java.lang
*/
interface TestForm {
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
    /**
     * 表单模式不支持泛型
     */
    generics?: Object[];
    generic?: Object;
}
export default TestForm;
