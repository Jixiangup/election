package com.bnyte.election.api.vo.election;

/**
 * 选举结果VO
 * @author bnyte
 * @since 1.0.0
 */
public class ElectionResultVO {

    /**
     * 选举人ID
     */
    private Long electionId;

    /**
     * 候选人ID
     */
    private Long candidateId;

    /**
     * 获票数
     */
    private Long count;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
