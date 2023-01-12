package com.bnyte.election.api.dao;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ElectionDetailDAO {

    /**
     * 用户主键id
     */
    private Long userid;

    /**
     * 选举id
     */
    private Long electionId;

    /**
     * 参与的候选人id
     */
    private Long candidateId;

    /**
     * 用户邮件
     */
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
