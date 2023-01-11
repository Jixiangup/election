package com.bnyte.election.api.common.enums;

/**
 * 分布式锁类型
 *  包含 redis, zk等多种方式可以实现分布式锁 这里为了后续扩容设计
 * @author bnyte
 * @since 1.0.0
 */
public enum EDistributedLockType {

    REDIS,
    ;

}
