package com.bnyte.election.api.entity;

/**
 * 选举和候选人关联关系表
 * @author bnyte
 * @since 1.0.0
 */
public class ElectionCandidate extends AbsAutoIdEntity {

    /**
     * 候选人id
     */
    private Long candidateId;

    /**
     * 当前选举号主键id
     */
    private Long electionId;

    /**
     * 当前获牌数量
     */
    private Long count;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
