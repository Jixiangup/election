package com.bnyte.election.api.service;

import com.bnyte.election.api.common.lang.http.Page;
import com.bnyte.election.api.dao.ElectionDetailDAO;
import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.ElectionCandidateDetail;
import com.bnyte.election.api.param.election.VoteDetailSearch;
import com.bnyte.election.api.vo.user.VoteInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 选举投票详情业务抽象
 * @author bnyte
 * @since 1.0.0
 */
public interface IElectionCandidateDetailService {
    ElectionCandidateDetail queryVoteByUserid(Serializable userid, Serializable electionId);

    void voteByUserid(VoteDTO dto);

    void save(ElectionCandidateDetail electionCandidateDetail);

    Page<VoteInfo> queryVoteDetailPage(VoteDetailSearch search);

    List<ElectionDetailDAO> queryVoteByElectionId(Long electionId);
}
