'use strict';



import User from './User'


/**
 * @author  zuoge85 on 15/6/17.
*/
class TestObjectArray {

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

    bytesValue:List;

    regDate:Date;

    booleanValueArray:List;

    intValueArray:List;

    longValueArray:List;

    floatValueArray:List;

    doubleValueArray:List;

    stringValueArray:List;

    regDateArray:List;

    user:User;

    users:List;

    generics:List;

    genericObjs:List;

    genericUsers:List;

    genericObj:TestObjectArray;

    generic:Object;
    constructor() {

    }

    formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic}):TestObjectArray{
        this.id = id;
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.longValue = longValue;
        this.floatValue = floatValue;
        this.doubleValue = doubleValue;
        this.stringValue = stringValue;
        this.bytesValue = bytesValue;
        this.regDate = regDate;
        this.booleanValueArray = booleanValueArray;
        this.intValueArray = intValueArray;
        this.longValueArray = longValueArray;
        this.floatValueArray = floatValueArray;
        this.doubleValueArray = doubleValueArray;
        this.stringValueArray = stringValueArray;
        this.regDateArray = regDateArray;
        this.user = user;
        this.users = users;
        this.generics = generics;
        this.genericObjs = genericObjs;
        this.genericUsers = genericUsers;
        this.genericObj = genericObj;
        this.generic = generic;
        return this;
    }

    static of({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic}):TestObjectArray{
        return new TestObjectArray().formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }

    static form(id:String,booleanValue:Boolean,intValue:Number,longValue:Number,floatValue:Number,doubleValue:Number,stringValue:String,bytesValue:List,regDate:Date,booleanValueArray:List,intValueArray:List,longValueArray:List,floatValueArray:List,doubleValueArray:List,stringValueArray:List,regDateArray:List,user:User,users:List,generics:List,genericObjs:List,genericUsers:List,genericObj:TestObjectArray,generic:Object):TestObjectArray{
        return new TestObjectArray().formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }
}

export default TestObjectArray;