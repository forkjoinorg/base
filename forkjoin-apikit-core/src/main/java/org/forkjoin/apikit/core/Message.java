package org.forkjoin.apikit.core;

import java.lang.annotation.*;

/**
 * @author zuoge85 on 15/4/25.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Message {
//    String value() default "";
}
