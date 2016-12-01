# base


## 2.0 第一个版本完成，可以使用

1. 去掉专门的配置文件模式，转而使用项目内java 文件识别
2. api 接口识别采取使用 接口得方式
3. java 客户端和 es 客户端生成已经可用


## 2.0 计划完成情况

2015.12.10 完成api 和message 解析

## 需要完成部分

1. 安卓客户端部分的测试
2. ios sdk生成部分
3. 枚举支持


***


## apikit
apikit 是一个生成http restfull 客户端和服务器接口的工具

1. 生成spring mvc 相关控制器 和form model
2. 生成 swift android javase 相关api 接口
3. 类似json rpc，但是不同于jsonrpc

apikit 带来的好处

1. 统一的接口定义，告别前后端对协议，显著减少前端编码
2. 统一的接口定义文档，根据注释生成标准的javadoc 文档，告别文档的不统一或者和代码脱节等问题
3. 强制前端根据接口修改代码
4. 提高api 开发效率
4. 标准的http restfull


## jdbckit
jdbckit 是一个生成简单对象映射的工具（spring jdbc template）

1. 生成简单对象，映射和简单的多条件查询
2. 不支持join和表关系

jdbckit 带来的好处

1. 简单
2. join效率问题，几乎所有sql 中间件都不能很好处理 join



## maven

`
<repositories>
  <repository>
    <id>maven-repository</id>
    <url>https://raw.githubusercontent.com/zuoge85/maven-repo/master/repository/</url>
  </repository>
</repositories>
`