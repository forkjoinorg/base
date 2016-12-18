'use strict';



/**
 * 用户信息
*/
class User {

    id:Number;

	/**
	 * 名称允许重复
	 */
    name:String;

	/**
	 * 年龄
	 */
    age:Number;
    constructor() {

    }

    static of({id,name,age}):User{
        return new User().formObject({id,name,age});
    }

    static form(id:Number,name:String,age:Number):User{
        return new User().formObject({id,name,age});
    }
}

export default User;