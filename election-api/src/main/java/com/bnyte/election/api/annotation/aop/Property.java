package com.bnyte.election.api.annotation.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁key模版转换取值对象标记注解
 * @author bnyte
 * @since 1.0.0
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Property {
}
