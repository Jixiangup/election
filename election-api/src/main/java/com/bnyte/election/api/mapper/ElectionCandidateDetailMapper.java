package com.bnyte.election.api.mapper;

import com.bnyte.election.api.dao.ElectionDetailDAO;
import com.bnyte.election.api.entity.ElectionCandidateDetail;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.param.election.VoteDetailSearch;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface ElectionCandidateDetailMapper {
    ElectionCandidateDetail selectVoteByUserid(@Param("userid") Serializable userid, @Param("electionId") Serializable electionId);

    void save(@Param("item") ElectionCandidateDetail electionCandidateDetail);

    List<User> selectVoteDetailPage(@Param("search") VoteDetailSearch search);

    List<ElectionDetailDAO> selectVoteByElectionId(@Param("electionId") Long electionId);
}
