package org.forkjoin.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zuoge85 on 15/4/25.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Account {
    String PARAM_NAME = "user";
    boolean value() default true;
    String param() default "";
}
