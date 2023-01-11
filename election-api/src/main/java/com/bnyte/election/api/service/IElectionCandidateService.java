package com.bnyte.election.api.service;

import com.bnyte.election.api.entity.ElectionCandidate;

import java.io.Serializable;
import java.util.List;

/**
 * 选举的候选人详情关系业务抽象
 * @author bnyte
 * @since 1.0.0
 */
public interface IElectionCandidateService {

    /**
     * 通过选举号主键id逻辑删除选举号和候选人的关联关系
     * @param electionId 选举号主键id
     */
    void removeOldCandidateOfElection(Serializable electionId);

    void saveBatch(List<ElectionCandidate> electionCandidates);

    List<ElectionCandidate> listByElectionId(Long electionId);

    ElectionCandidate queryElectionByCandidateId(Long electionId, Long candidateId);

    void updateById(ElectionCandidate electionCandidate);
}
