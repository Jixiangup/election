package com.bnyte.election.api.param.election;

import com.bnyte.election.api.param.AbsSearch;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class VoteDetailSearch extends AbsSearch {

    /**
     * 发起查询用户id
     *  校验必须在本次投票中投过票或者是管理员可查看详情
     */
    private Long userid;

    /**
     * 选举id
     */
    private Long electionId;

    /**
     * 候选人ID
     */
    private Long candidateId;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
