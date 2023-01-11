package com.bnyte.election.api.mapper;

import com.bnyte.election.api.entity.ElectionCandidateDetail;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface ElectionCandidateDetailMapper {
    ElectionCandidateDetail selectVoteByUserid(@Param("userid") Serializable userid, @Param("electionId") Serializable electionId);

    void save(@Param("item") ElectionCandidateDetail electionCandidateDetail);
}
