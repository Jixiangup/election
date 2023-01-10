package com.bnyte.election.api.dto.election;

import com.bnyte.election.api.common.enums.EElectionStatus;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class EditorElectionDTO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 当前状态
     * @see com.bnyte.election.api.common.enums.EElectionStatus
     */
    private Integer status = EElectionStatus.UN_START.getCode();

    /**
     * 参加本次选举的候选人主键集合
     */
    private List<Long> candidateIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getCandidateIds() {
        return candidateIds;
    }

    public void setCandidateIds(List<Long> candidateIds) {
        this.candidateIds = candidateIds;
    }
}
