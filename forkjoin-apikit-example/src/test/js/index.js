'use strict';

import ApiList from './collecton/ApiList';
import TestForm from './form/TestForm';
import TestObject from './model/TestObject';
import User from './model/User';
import Apis from "./Apis";


var Objects:{
    ApiList:ApiList,
    TestForm:TestForm,
    TestObject:TestObject,
    User:User
} = {
    ApiList:ApiList,
    TestForm:TestForm,
    TestObject:TestObject,
    User:User
};

export default Objects;

export {
    ApiList,
    TestForm,
    TestObject,
    User,
    Apis
};