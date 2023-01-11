package com.bnyte.election.api.service;

import com.bnyte.election.api.common.enums.EElectionStatus;
import com.bnyte.election.api.dto.election.EditorElectionDTO;
import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.Election;

import java.io.Serializable;

/**
 * <p>
 *     选举业务抽象接口
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface IElectionService {
    Long editor(EditorElectionDTO payload2EditorDTO);

    Election queryById(Serializable id);

    void updateById(Election election);

    void save(Election election);

    void saveOrUpdateById(Election election);

    void start(Long id);

    Election editorStatus(Long id, EElectionStatus targetStatus);

    void vote(VoteDTO dto);

    void finish(Long id);
}
