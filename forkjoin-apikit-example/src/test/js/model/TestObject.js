'use strict';



import User from './User'


/**
 * @author  zuoge85 on 15/6/17.
*/
class TestObject {

    testForm:TestForm;

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

    formObject({testForm,id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic}):TestObject{
        this.testForm = testForm;
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

    static of({testForm,id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic}):TestObject{
        return new TestObject().formObject({testForm,id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }

    static form(testForm:TestForm,id:String,booleanValue:Boolean,intValue:Number,longValue:Number,floatValue:Number,doubleValue:Number,stringValue:String,bytesValue:Number[],regDate:Date,booleanValueArray:Boolean[],intValueArray:Number[],longValueArray:Number[],floatValueArray:Number[],doubleValueArray:Number[],stringValueArray:String[],regDateArray:Date[],user:User,users:User[],generics:Object[],genericObjs:TestObject[],genericUsers:TestObject[],genericObj:TestObject,generic:Object):TestObject{
        return new TestObject().formObject({testForm,id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }
}

export default TestObject;