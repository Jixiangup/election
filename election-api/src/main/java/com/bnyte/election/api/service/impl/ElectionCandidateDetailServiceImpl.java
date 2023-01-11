package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.ElectionCandidateDetail;
import com.bnyte.election.api.mapper.ElectionCandidateDetailMapper;
import com.bnyte.election.api.service.IElectionCandidateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

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
}
