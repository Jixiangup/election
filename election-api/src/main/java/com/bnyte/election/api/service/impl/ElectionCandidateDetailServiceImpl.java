package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.common.lang.http.Page;
import com.bnyte.election.api.common.util.PageUtils;
import com.bnyte.election.api.dao.ElectionDetailDAO;
import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.ElectionCandidateDetail;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.mapper.ElectionCandidateDetailMapper;
import com.bnyte.election.api.mapstruct.user.UserTransfer;
import com.bnyte.election.api.param.election.VoteDetailSearch;
import com.bnyte.election.api.service.IElectionCandidateDetailService;
import com.bnyte.election.api.vo.user.VoteInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 选举投票详情业务抽象实现
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class ElectionCandidateDetailServiceImpl implements IElectionCandidateDetailService {

    @Autowired
    ElectionCandidateDetailMapper electionCandidateDetailMapper;

    @Override
    public ElectionCandidateDetail queryVoteByUserid(Serializable userid, Serializable electionId) {
        return electionCandidateDetailMapper.selectVoteByUserid(userid, electionId);
    }

    @Override
    public void voteByUserid(VoteDTO dto) {
        ElectionCandidateDetail detail = new ElectionCandidateDetail();
        detail.setUserid(dto.getUserid());
        detail.setElectionId(dto.getElectionId());
        detail.setCandidateId(dto.getCandidateId());
        detail.setCreateTime(new Date());
        detail.setModifiedTime(new Date());
        detail.setDeleted(false);
        save(detail);
    }

    @Override
    public void save(ElectionCandidateDetail electionCandidateDetail) {
        electionCandidateDetailMapper.save(electionCandidateDetail);
    }

    @Override
    public Page<VoteInfo> queryVoteDetailPage(VoteDetailSearch search) {
        PageHelper.startPage(search.getCurrent().intValue(), search.getQueryCount().intValue());
        List<User> users = electionCandidateDetailMapper.selectVoteDetailPage(search);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        Page<VoteInfo> voteInfoPage = PageUtils.pageTransfer(pageInfo, VoteInfo.class);
        voteInfoPage.setRecords(UserTransfer.INSTANCE.entityToVoteInfo(pageInfo.getList()));
        return voteInfoPage;
    }

    @Override
    public List<ElectionDetailDAO> queryVoteByElectionId(Long electionId) {
        return electionCandidateDetailMapper.selectVoteByElectionId(electionId);
    }
}
