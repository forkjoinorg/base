# apikit 和jdbckit 工具集合


[apikit 文档](./forkjoin-apikit/README.md)


api kit 是一个根据spring mvc 生成http RESTful 客户端 sdk 和doc 的工具
现在支持生成java client sdk 和 es6 client sdk，项目已经成功应用在多个项目

只需稍微改造项目， 就可以生成远程调用代码（存根）

------------------------------------------------------------


下面是一个简易的js 调用例子:
````javascript
this.commons.apis.accountApi.login(LoginForm.form(
    "123456", "13910831723"
)).then((m: LoginRet) => {
    
}).default();
````

***



## jdbckit
jdbckit 是一个生成简单对象映射的工具（spring jdbc template）

1. 生成简单对象，映射和简单的多条件查询
2. 支持 字段映射成json 和xml
3. 强类型复杂查询

jdbckit 优点

1. 简单
2. 强类型



## maven

`
现在已经提交到 maven 中心库

<dependency>
    <groupId>org.forkjoin</groupId>
    <artifactId>forkjoin-apikit</artifactId>
    <version>2.1.1</version>
</dependency>
