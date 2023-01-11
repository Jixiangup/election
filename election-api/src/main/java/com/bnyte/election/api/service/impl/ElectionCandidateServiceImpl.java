package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.entity.ElectionCandidate;
import com.bnyte.election.api.mapper.ElectionCandidateMapper;
import com.bnyte.election.api.service.IElectionCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 选举的候选人详情关系业务抽象实现
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class ElectionCandidateServiceImpl implements IElectionCandidateService {

    @Autowired
    ElectionCandidateMapper electionCandidateMapper;

    @Override
    @Transactional
    public void removeOldCandidateOfElection(Serializable electionId) {
        electionCandidateMapper.deleteOldCandidateOfElection(electionId);
    }

    @Override
    public void saveBatch(List<ElectionCandidate> electionCandidates) {
        electionCandidateMapper.insertBatch(electionCandidates);
    }

    @Override
    public List<ElectionCandidate> listByElectionId(Long electionId) {
        return electionCandidateMapper.selectListByElectionId(electionId);
    }

    @Override
    public ElectionCandidate queryElectionByCandidateId(Long electionId, Long candidateId) {
        return electionCandidateMapper.selectElectionByCandidateId(electionId, candidateId);
    }

    @Override
    public void updateById(ElectionCandidate electionCandidate) {
        electionCandidateMapper.updateById(electionCandidate);
    }
}
