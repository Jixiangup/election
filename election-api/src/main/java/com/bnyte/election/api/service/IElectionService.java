package com.bnyte.election.api.service;

import com.bnyte.election.api.dto.election.EditorElectionDTO;

/**
 * <p>
 *     选举业务抽象接口
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface IElectionService {
    Long editor(EditorElectionDTO payload2EditorDTO);
}
