package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.dto.election.EditorElectionDTO;
import com.bnyte.election.api.mapper.ElectionMapper;
import com.bnyte.election.api.service.IElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *     选举业务抽象接口实现
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class IElectionServiceImpl implements IElectionService {

    @Autowired
    ElectionMapper electionMapper;

    @Override
    @Transactional
    public Long editor(EditorElectionDTO payload2EditorDTO) {

        // 删除旧数据

        // 保存选举

        // 写候选人和选举关联关系

        return null;
    }
}
