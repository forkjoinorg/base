'use strict';

import ApiList from './collecton/ApiList';
import TestForm from './form/TestForm';
import TestObject from './model/TestObject';
import TestObjectList from './model/TestObjectList';
import User from './model/User';
import Apis from "./Apis";

const collecton = {
    ApiList:ApiList
};

const form = {
    TestForm:TestForm
};

const model = {
    TestObject:TestObject,
    TestObjectList:TestObjectList,
    User:User
};


export default Apis;

export {
    collecton,
    form,
    model,
    ApiList,
    TestForm,
    TestObject,
    TestObjectList,
    User,
};