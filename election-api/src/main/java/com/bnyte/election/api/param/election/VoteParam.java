package com.bnyte.election.api.param.election;

import com.bnyte.election.api.common.enums.EElectionStatus;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * 投票请求参数
 * @author bnyte
 * @since 1.0.0
 */
public class VoteParam {

    /**
     * 选举号主键id
     */
    private Long electionId;

    /**
     * 投票的用户id
     */
    private Long userid;

    /**
     * 选举号对应的候选人id
     */
    private Long candidateId;

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

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
