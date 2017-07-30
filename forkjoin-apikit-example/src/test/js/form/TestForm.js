'use strict';



/**
 * 好吧，测试表单
 * <p>
 * <p>
 * <h1>好呀</h1>
 * @author  zuoge85 on 15/4/18.
 * @see java.lang
*/
class TestForm {

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

	/**
	 * 表单模式不支持泛型
	 */
    generics:Object[];

    genericObjs:TestObject[];

    genericUsers:TestObject[];

    genericObj:TestObject;

    generic:Object;
    constructor() {

    }

    formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic}):TestForm{
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

    static of({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic}):TestForm{
        return new TestForm().formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }

    static form(id:String,booleanValue:Boolean,intValue:Number,longValue:Number,floatValue:Number,doubleValue:Number,stringValue:String,bytesValue:Number[],regDate:Date,booleanValueArray:Boolean[],intValueArray:Number[],longValueArray:Number[],floatValueArray:Number[],doubleValueArray:Number[],stringValueArray:String[],regDateArray:Date[],user:User,users:User[],generics:Object[],genericObjs:TestObject[],genericUsers:TestObject[],genericObj:TestObject,generic:Object):TestForm{
        return new TestForm().formObject({id,booleanValue,intValue,longValue,floatValue,doubleValue,stringValue,bytesValue,regDate,booleanValueArray,intValueArray,longValueArray,floatValueArray,doubleValueArray,stringValueArray,regDateArray,user,users,generics,genericObjs,genericUsers,genericObj,generic});
    }
}

export default TestForm;