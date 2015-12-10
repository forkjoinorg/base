# base


2.0 开发中，请勿生产环境使用

## 2.0 计划完成情况

2015.12.10 完成api 和message 解析

## 2.0 展望

1. 类型修改，特别是数组
2. 架构重构, 去掉不合理依赖,重构成微内核模式
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