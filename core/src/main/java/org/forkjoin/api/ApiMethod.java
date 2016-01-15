package org.forkjoin.api;

import java.lang.annotation.*;

/**
 * value 表示url 地址,spring mvc 样式
 * type 表示为ActionType
 * 如果是http 协议 映射成 http method
 * @author zuoge85 on 15/4/25.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMethod {
    String value();
    ActionType type() default ActionType.GET;
}
