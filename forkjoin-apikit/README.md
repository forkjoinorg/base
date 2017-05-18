# api kit

api kit 是一个根据spring mvc 生成http RESTful 客户端 sdk 和doc 的工具
现在支持生成java client sdk 和 es6 client sdk，项目已经成功应用在多个项目

只需稍微改造项目， 就可以生成远程调用代码（存根）

-------------------------------------------

## 一. example

这是一个基于spring boot 的范例项目

代码请查看  [forkjoin-apikit-example](./forkjoin-apikit-example)

### 1.访问
启动 ExampleApplication 主类，将启动spring boot 


浏览器打开

1. json: http://127.0.0.1:8080/v1/test.json
2. xml: http://127.0.0.1:8080/v1/test.json

### 2.执行生成器

启动 ApiBuilderMain 主类将在test 目录下生成测试java 和js 客户端

-------------------------------------------

## 二. JS 实现

网络请求是标准的Promise ，唯一注意的地方是加入了default 函数处理错误情况！必须调用
支持Promise 相关并行，串行等操作

1. 修改服务器协议地址

修改 src/app.js 文件
下的
Apis.init("http://127.0.0.1:8080/","v1");

2. 简单调用

````javascript
this.commons.apis.accountApi.login(LoginForm.form(
    "123456", "13910831723"
)).then((m: LoginRet) => {
    
}).default();
````


3.串联

```javascript
this.commons.apis.accountApi.login(LoginForm.form(
    "123456", "13910831723"
)).then((m: LoginRet) => {
    return this.commons.apis.testApi.test()
}).then((o: TestObject) => {
    return this.commons.apis.testApi.test()
}).default();
```

4.并行

```javascript
Promise.all(
    [
        this.commons.apis.accountApi.login(LoginForm.form(
            "123456", "13910831723"
        )),
        this.commons.apis.testApi.test()
    ]
).then((m:LoginRet,o:TestObject)=>{

}).default();
```


#### commons 使用例子如下

````javascript
import React, {Component, PropTypes} from "react";
import {Text, View} from "react-native";
import Apis, {LoginForm, LoginRet} from "app-js-sdk";
import {Commons} from "commons";

export default class IndexView extends Component {
    commons: Commons = new Commons().bind(this);

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        //登录服务器
        this.commons.apis.accountApi.login(LoginForm.form(
            "123456", "13910831723"
        )).then((m: LoginRet) => {

        }).default();

        //定时任务
        this.commons.setTimeout(()=>{

        },500)
    }

    render() {
        return <View></View>;
    }
}

console.log("IndexView", IndexView);
````
