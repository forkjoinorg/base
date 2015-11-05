package org.forkjoin.api;

import java.lang.annotation.*;

/**
 * @author zuoge85 on 15/4/25.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMethod {
    String value() default "";
    ActionType type() default ActionType.GET;
}
