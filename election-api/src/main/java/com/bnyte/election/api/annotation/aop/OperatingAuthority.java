package com.bnyte.election.api.annotation.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证用户是否拥有操作权限
 * 实现逻辑 {@link com.bnyte.election.api.aspectj.OperatingAuthorityAspectj}
 * @author bnyte
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OperatingAuthority {
}
