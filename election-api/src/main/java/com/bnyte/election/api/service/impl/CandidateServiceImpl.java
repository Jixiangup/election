package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.common.lang.http.Status;
import com.bnyte.election.api.dto.candidate.EditorCandidateDTO;
import com.bnyte.election.api.entity.Candidate;
import com.bnyte.election.api.exception.ParameterCheckException;
import com.bnyte.election.api.mapper.CandidateMapper;
import com.bnyte.election.api.service.ICandidateService;
import com.bnyte.election.api.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;

/**
 * 候选人抽象业务接口实现
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class CandidateServiceImpl implements ICandidateService {

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    @Transactional
    public Long editor(EditorCandidateDTO dto) {

        Candidate candidate;

        // 新增
        if (null == dto.getId() || dto.getId() < 1) {
            // 校验重复数据
            candidate = candidateMapper.selectWithNickname(dto.getNickname());
            if (ObjectUtils.nonNull(candidate)) {
                throw new ParameterCheckException(Status.CANDIDATE_NICKNAME_EXISTS);
            }

        }

        // 构建编辑的候选人信息
        candidate = buildEditorCandidate(dto);

        saveOrUpdateById(candidate);

        return candidate.getId();
    }

    /**
     * 构建候选人信息
     *  通过id识别是新增或者更新
     *  id小于0或为空时为新增 反之更新
     * @param dto 登记用户信息DTO
     * @return 需要保存的登记信息
     */
    private Candidate buildEditorCandidate(EditorCandidateDTO dto) {
        Candidate candidate = queryById(dto.getId());

        if (ObjectUtils.isNull(candidate)) {
            candidate = new Candidate();
            candidate.setCreateTime(new Date());
        }
        candidate.setDeleted(false);
        candidate.setModifiedTime(new Date());
        candidate.setNickname(dto.getNickname());

        return candidate;
    }

    @Override
    public Candidate queryWithNickname(String nickname) {
        return candidateMapper.selectWithNickname(nickname);
    }

    @Override
    public Candidate queryById(Serializable id) {
        return candidateMapper.selectById(id);
    }

    @Override
    public Long saveOrUpdateById(Candidate candidate) {
        if (ObjectUtils.isNull(candidate.getId()) || candidate.getId() < 1) {
            return candidateMapper.insert(candidate);
        } else {
            return candidateMapper.updateById(candidate);
        }
    }

}
