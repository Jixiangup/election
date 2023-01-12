package com.bnyte.election.api.async;

/**
 * 邮箱异步任务接口
 * @author bnyte
 * @since 1.0.0
 */
public interface IAsyncMailTaskService {

    void senderElectionNotice(Long id);
}
