package com.bnyte.election.api.entity;

/**
 * 平台选举和候选人投票详情
 * @author bnyte
 * @since 1.0.0
 */
public class ElectionCandidateDetail extends AbsAutoIdEntity {

    /**
     * 候选人id
     */
    private Long candidateId;

    /**
     * 当前选举号主键id
     */
    private Long electionId;

    /**
     * 投票用户id
     */
    private Long userid;

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
