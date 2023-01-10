package com.bnyte.election.api.param.election;

import com.bnyte.election.api.common.enums.EElectionStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * 编辑选举前端交互请求参数
 * @author bnyte
 * @since 1.0.0
 */
public class EditorElectionParam {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 当前状态
     * @see com.bnyte.election.api.common.enums.EElectionStatus
     * 默认指为未启用
     */
    private Integer status = EElectionStatus.UN_START.getCode();

    /**
     * 参加本次选举的候选人主键集合
     */
    @Size(min = 2, message = "一次选举至少需要两个候选人")
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
