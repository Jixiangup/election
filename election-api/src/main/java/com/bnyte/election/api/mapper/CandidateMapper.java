package com.bnyte.election.api.mapper;

import com.bnyte.election.api.entity.Candidate;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *     候选人ORM抽象接口
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface CandidateMapper {
    Candidate selectWithNickname(@Param("nickname") String nickname);

    Candidate selectById(@Param("id") Serializable id);

    Long insert(@Param("item") Candidate candidate);

    Long updateById(@Param("item") Candidate candidate);
}
