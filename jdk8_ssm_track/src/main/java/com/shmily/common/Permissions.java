package com.shmily.common;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wuxubiao on 2017/4/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Permissions {
    String value() default "";
}
