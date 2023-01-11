package com.bnyte.election.api.service;

import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.ElectionCandidateDetail;

import java.io.Serializable;

/**
 * 选举投票详情业务抽象
 * @author bnyte
 * @since 1.0.0
 */
public interface IElectionCandidateDetailService {
    ElectionCandidateDetail queryVoteByUserid(Serializable userid, Serializable electionId);

    void voteByUserid(VoteDTO dto);

    void save(ElectionCandidateDetail electionCandidateDetail);
}
