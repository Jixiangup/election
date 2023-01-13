package com.bnyte.election.api.service;

import com.bnyte.election.api.common.enums.EElectionStatus;
import com.bnyte.election.api.common.lang.http.Page;
import com.bnyte.election.api.dto.election.EditorElectionDTO;
import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.Election;
import com.bnyte.election.api.entity.ElectionCandidate;
import com.bnyte.election.api.param.election.VoteDetailSearch;
import com.bnyte.election.api.vo.user.VoteInfo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *     选举业务抽象接口
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface IElectionService {

    /**
     * 编辑选举
     * @param dto 前端请求参数dto
     * @return 选举id
     */
    Long editor(EditorElectionDTO dto);

    /**
     * 通过主键查询id选举
     * @param id 主键id
     * @return 选举
     */
    Election queryById(Serializable id);

    /**
     * 通过主键id更新选举
     * @param election 选举
     */
    void updateById(Election election);

    /**
     * 保存选举
     * @param election 选举对象
     */
    void save(Election election);

    /**
     * 保存或通过主键id更新选举
     * @param election 选举
     */
    void saveOrUpdateById(Election election);

    /**
     * 开始选举
     * @param id 选举主键ID
     */
    void start(Long id);

    /**
     * 编辑选举状态
     * @param id 选举珠江id
     * @param targetStatus 需要更新的目标状态
     * @return 选举对象
     */
    Election editorStatus(Long id, EElectionStatus targetStatus);

    /**
     * 给选举发起投票
     * @param dto 选举dto对象
     */
    List<ElectionCandidate> vote(VoteDTO dto);

    /**
     * 结束选举
     * @param id 选举主键ID
     */
    void finish(Long id);

    /**
     * 分页获取投票详情
     * @param search 分页参数
     * @return 返回分页数据
     */
    Page<VoteInfo> pageVoteDetail(VoteDetailSearch search);

    Long candidateVoteCount(Long electionId, Long candidateId);

    List<ElectionCandidate> result(Long id);
}
