package com.bnyte.election.api.mapper;

import com.bnyte.election.api.entity.ElectionCandidate;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface ElectionCandidateMapper {

    void deleteOldCandidateOfElection(@Param("electionId") Serializable electionId);

    void insertBatch(@Param("list") List<ElectionCandidate> electionCandidates);

    List<ElectionCandidate> selectListByElectionId(@Param("electionId") Long electionId);

    ElectionCandidate selectElectionByCandidateId(@Param("electionId") Serializable electionId, @Param("candidateId") Serializable candidateId);

    void updateById(@Param("item") ElectionCandidate electionCandidate);
}
