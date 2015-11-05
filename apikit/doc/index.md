# ApiKit


当前版本定义文件是java文件，使用eclipse jdt 分析java 文件生成model 树，
然后传入生成器生成相关代码。

## 定义文件

1. api定义文件

    类前面有 Api 注解的识别为 “api定义文件”
    
    函数前面有ApiMethod 注解的识别为 “api方法”
    
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





