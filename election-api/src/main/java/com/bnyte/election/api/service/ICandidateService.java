package com.bnyte.election.api.service;

import com.bnyte.election.api.dto.candidate.EditorCandidateDTO;
import com.bnyte.election.api.entity.Candidate;

import java.io.Serializable;

/**
 * 候选人业务抽象接口
 * @author bnyte
 * @since 1.0.0
 */
public interface ICandidateService {

    /**
     * 编辑候选人
     *  id小于0或为空时则为新增 反之更新
     * @param dto 请求参数dto
     * @return 数据主键id
     */
    Long editor(EditorCandidateDTO dto);

    /**
     * 通过昵称查询候选人
     * @param nickname 候选人昵称
     * @return 返回候选人详情, 如果不存在则为空
     */
    Candidate queryWithNickname(String nickname);

    /**
     * 通过主键id查询候选人
     * @param id 主键id
     * @return 返回候选人详情, 如果不存在则为空
     */
    Candidate queryById(Serializable id);

    /**
     * 添加或更新候选人
     *  id小于0或为空时则为新增 反之更新
     * @param candidate 带编辑的候选人实体对象
     * @return 影响行数
     */
    Long saveOrUpdateById(Candidate candidate);
}
