# ApiKit


当前版本定义文件是java文件，使用eclipse jdt 分析java 文件生成model 树，
然后传入生成器生成相关代码。

## 定义文件

1. api定义文件

    类前面有 Api 注解的识别为 “api定义文件”
    
    函数前面有ApiMethod 注解的识别为 “api方法”
        
    1. value 为restfull url
    2. type 为 ActionType 映射位http method（默认为GET）
    
>    例如：
>    
>```
>    @Api
>    public interface BaseApi {
>        @ApiMethod(value = "base/", type = ActionType.CREATE)
>        User create(@Valid User user);
>        
>        @ApiMethod(value = "base/{id}", type = ActionType.GET)
>        User get(@PathVariable String id);
>    }
>```

2. Message定义文件

    类前面有 Message 注解的识别为 “Message定义文件”
    
    内部所有字段都被识别为Message字段，最后会被处理位属性，其他注解会被保留，
    根据不同的代码生成器决定是否保留其他注解
    
>   例如：
>
>```
>    @Message
>    public class User {
>    
>        long id;
>    
>        /**
>         * 名称允许重复
>         */
>        @Length(max = 255)
>        String name;
>    
>        /**
>         * 座机电话
>         */
>        String telephone;
>        /**
>         * 手机电话
>         */
>        String mobile;
>    }
>```
