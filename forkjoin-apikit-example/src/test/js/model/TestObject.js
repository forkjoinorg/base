'use strict';



import User from './User'
import User from './User'


/**
 * @author  zuoge85 on 15/6/17.
*/
class TestObject {

    id:String;

	/**
	 * 签名
	 * @see java.lang
	 * java.lang
	 */
    booleanValue:Boolean;

    intValue:Number;

    longValue:Number;

    floatValue:Number;

    doubleValue:Number;

    stringValue:String;

    bytesValue:Number[];

    regDate:Date;

    booleanValueArray:Boolean[];

    intValueArray:Number[];

    longValueArray:Number[];

    floatValueArray:Number[];

    doubleValueArray:Number[];

    stringValueArray:String[];

    regDateArray:Date[];

    user:User;

    users:User[];

    generics:Object[];

    genericObjs:TestObject[];

    genericUsers:TestObject[];

    genericObj:TestObject;

    generic:Object;
    constructor() {

    }

    static of({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic}):TestObject{
        return new TestObject().formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }

    static form(id:String,booleanValue:Boolean,intValue:Number,longValue:Number,floatValue:Number,doubleValue:Number,stringValue:String,bytesValue:Number[],regDate:Date,booleanValueArray:Boolean[],intValueArray:Number[],longValueArray:Number[],floatValueArray:Number[],doubleValueArray:Number[],stringValueArray:String[],regDateArray:Date[],user:User,users:User[],generics:Object[],genericObjs:TestObject[],genericUsers:TestObject[],genericObj:TestObject,generic:Object):TestObject{
        return new TestObject().formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }
}

export default TestObject;