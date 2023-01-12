package com.bnyte.election.api.common.constant;

/**
 * 邮件常量
 * @author bnyte
 * @since 1.0.0
 */
public class MailConstant {

    /**
     * 发件人
     */
    public static final String FROM_MAIL = "bnytezz@163.com";

    /**
     * 选举通知主题
     */
    public static final String ELECTION_NOTIFICATION_SUBJECT = "Election选举结果通知";

    /**
     * 发送选举内容模版
     */
    public static final String ELECTION_CONTENT_TEMPLATE = "用户您好, 您参与的选举 ${electionId} 投票已经结束了, 以下是投票结果:";

    /**
     * 发送选举内容候选人获票结果模版
     */
    public static final String ELECTION_CANDIDATE_VOTE_RESULT_TEMPLATE = "候选人 ${candidateId} 获得的总票数是: ${count} 票";

}
