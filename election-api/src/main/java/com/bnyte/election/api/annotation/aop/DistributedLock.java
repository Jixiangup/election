package com.bnyte.election.api.annotation.aop;

import com.bnyte.election.api.common.enums.EDistributedLockType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁注解
 * @see com.bnyte.election.api.aspectj.DistributedLockAspectj
 * @author bnyte
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    /**
     * 分布式锁的key
     *  支持字符串模版,使用模版需要配合 {@link Property} 一起使用
     * @return 分布式锁key
     */
    String lockKey();

    /**
     * 当前分布式锁使用框架实现类型
     * @return 分布式锁类型
     */
    EDistributedLockType lockType();


}
