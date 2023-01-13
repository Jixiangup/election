package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.async.IAsyncMailTaskService;
import com.bnyte.election.api.common.constant.RedisConstant;
import com.bnyte.election.api.common.enums.EElectionStatus;
import com.bnyte.election.api.common.lang.http.Page;
import com.bnyte.election.api.common.lang.http.Status;
import com.bnyte.election.api.common.util.StringUtils;
import com.bnyte.election.api.dto.election.EditorElectionDTO;
import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.Election;
import com.bnyte.election.api.entity.ElectionCandidate;
import com.bnyte.election.api.entity.ElectionCandidateDetail;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.exception.ParameterCheckException;
import com.bnyte.election.api.mapper.ElectionMapper;
import com.bnyte.election.api.param.election.VoteDetailSearch;
import com.bnyte.election.api.service.IElectionCandidateDetailService;
import com.bnyte.election.api.service.IElectionCandidateService;
import com.bnyte.election.api.service.IElectionService;
import com.bnyte.election.api.common.util.ObjectUtils;
import com.bnyte.election.api.service.IUserService;
import com.bnyte.election.api.vo.user.VoteInfo;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 *     选举业务抽象接口实现
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class IElectionServiceImpl implements IElectionService {

    @Autowired
    ElectionMapper electionMapper;

    @Autowired
    IElectionCandidateService electionCandidateService;

    @Autowired
    IUserService userService;

    @Autowired
    IAsyncMailTaskService asyncMailTaskService;

    @Autowired
    RedisTemplate<String, Long> redisString2LongTemplate;

    @Autowired
    IElectionCandidateDetailService electionCandidateDetailService;

    @Override
    @Transactional
    public Long editor(EditorElectionDTO payload2EditorDTO) {

        Election election = queryById(payload2EditorDTO.getId());
        if (ObjectUtils.isNull(election)) {
            election = new Election();
            election.setStatus(EElectionStatus.UN_START.getCode());
            election.setDeleted(false);
            election.setCreateTime(new Date());
            election.setModifiedTime(new Date());
        }

        if (!election.getStatus().equals(EElectionStatus.UN_START.getCode())) {
            throw new ParameterCheckException(Status.CURRENT_STATE_IS_NOT_OPERATIONAL);
        }



        saveOrUpdateById(election);

        // 删除旧数据
        electionCandidateService.removeOldCandidateOfElection(election.getId());

        // 保存选举
        Election finalElection = election;
        List<ElectionCandidate> electionCandidates = payload2EditorDTO.getCandidateIds().stream().map(e -> {
            ElectionCandidate electionCandidate = new ElectionCandidate();
            electionCandidate.setCandidateId(e);
            electionCandidate.setElectionId(finalElection.getId());
            electionCandidate.setCount(0L);

            electionCandidate.setCreateTime(new Date());
            electionCandidate.setModifiedTime(new Date());
            electionCandidate.setDeleted(false);
            return electionCandidate;
        }).toList();

        // 写候选人和选举关联关系
        electionCandidateService.saveBatch(electionCandidates);

        // 更新选举号信息
        election.setModifiedTime(new Date());
        saveOrUpdateById(election);

        return election.getId();
    }

    @Override
    public Election queryById(Serializable id) {
        return electionMapper.selectById(id);
    }

    @Override
    public void updateById(Election election) {
        electionMapper.updateById(election);
    }

    @Override
    public void save(Election election) {
        electionMapper.insert(election);
    }

    @Override
    public void saveOrUpdateById(Election election) {
        if (ObjectUtils.isNull(election.getId()) || election.getId() < 1) {
            save(election);
        } else {
            updateById(election);
        }
    }

    @Override
    @Transactional
    public void start(Long id) {

        Election election = ((IElectionService) AopContext.currentProxy()).editorStatus(id, EElectionStatus.STARTED);

        // 获取选举的候选人列表
        List<ElectionCandidate> electionCandidates = electionCandidateService.listByElectionId(election.getId());

        Properties properties = new Properties();
        properties.setProperty("electionId", election.getId().toString());
        // 这里不需要校验是不是为空因为前面校验了如果选举号为空会拦截 而如果不为空在创建选举的时候必须候选人 >= 2
        for (ElectionCandidate electionCandidate : electionCandidates) {
            // 写选举情况到缓存
            properties.setProperty("candidateId", electionCandidate.getCandidateId().toString());
            redisString2LongTemplate.opsForValue().increment(StringUtils.replacePlaceholders(RedisConstant.Election.NUMBER_OF_VOTES, properties), 0);
        }
    }

    @Override
    @Transactional
    public Election editorStatus(Long id, EElectionStatus targetStatus) {
        if (ObjectUtils.isNull(id)) {
            throw new ParameterCheckException(Status.DATA_NOT_FOUND);
        }

        Election election = queryById(id);
        if (ObjectUtils.isNull(election)) {
            throw new ParameterCheckException(Status.DATA_NOT_FOUND);
        }

        if (EElectionStatus.STARTED.getCode().equals(election.getStatus())) {
            throw new ParameterCheckException(Status.CURRENT_STATE_IS_NOT_OPERATIONAL);
        }

        election.setStatus(targetStatus.getCode());
        updateById(election);

        return election;
    }

    @Override
    public List<ElectionCandidate> vote(VoteDTO dto) {
        // 前置校验
        votePreCheck(dto);

        // 投票
        electionCandidateDetailService.voteByUserid(dto);

        // 缓存+1
        Properties properties = new Properties();
        properties.setProperty("candidateId", dto.getCandidateId().toString());
        properties.setProperty("electionId", dto.getElectionId().toString());
        Long increment = redisString2LongTemplate.opsForValue().increment(StringUtils.replacePlaceholders(RedisConstant.Election.NUMBER_OF_VOTES, properties));
        // 更新数据库的数量
        if (ObjectUtils.nonNull(increment) && increment > 9 && increment % 10 == 0) {
            ElectionCandidate electionCandidate = electionCandidateService.queryElectionByCandidateId(dto.getElectionId(), dto.getCandidateId());
            electionCandidate.setCount(increment);
            electionCandidateService.updateById(electionCandidate);
        }

        return result(dto.getElectionId());
    }

    @Override
    @Transactional
    public void finish(Long id) {

        finishPreCheck(id);

        Election election = queryById(id);
        election.setStatus(EElectionStatus.FINISHED.getCode());
        updateById(election);

        Properties properties = new Properties();
        properties.setProperty("electionId", id.toString());
        List<ElectionCandidate> electionCandidates = electionCandidateService.listByElectionId(id);
        for (ElectionCandidate electionCandidate : electionCandidates) {
            properties.setProperty("candidateId", electionCandidate.getCandidateId().toString());
            String key = StringUtils.replacePlaceholders(RedisConstant.Election.NUMBER_OF_VOTES, properties);
            Long increment = redisString2LongTemplate.opsForValue().get(key);

            // 同步投票结果
            electionCandidate.setCount(increment);
            electionCandidateService.updateById(electionCandidate);

            // 删除key
            redisString2LongTemplate.delete(key);
        }

        // 发送邮件通知
        // 这个位置应该吧完成的选举号发到 mq 去 让mq消费保证每条通知推广发出去 但是这里没那个条件就不这样写了
        // 这种方式如果发消息失败那就会出现redis和db的事务问题 所以这里的更好解决方案是 丢 mq 管理 mark一下有时间可以优化一下
        asyncMailTaskService.senderElectionNotice(id);
    }

    @Override
    public Page<VoteInfo> pageVoteDetail(VoteDetailSearch search) {
        // 校验是否符合查询条件 在本次选举投票过 或者是管理员
        User user = userService.queryById(search.getUserid());
        if (ObjectUtils.isNull(user)) {
            ElectionCandidateDetail electionCandidateDetail = electionCandidateDetailService.queryVoteByUserid(search.getUserid(), search.getElectionId());
            if (ObjectUtils.isNull(electionCandidateDetail)) {
                throw new ParameterCheckException(Status.NEED_TO_VOTE_FIRST);
            }
        }
        return electionCandidateDetailService.queryVoteDetailPage(search);
    }

    @Override
    public Long candidateVoteCount(Long electionId, Long candidateId) {
        Properties properties = new Properties();
        properties.setProperty("electionId", electionId.toString());
        properties.setProperty("candidateId", candidateId.toString());
        Long count = redisString2LongTemplate.opsForValue().get(StringUtils.replacePlaceholders(RedisConstant.Election.NUMBER_OF_VOTES, properties));
        if (null == count) {
            ElectionCandidate electionCandidate = electionCandidateService.queryElectionByCandidateId(electionId, candidateId);
            if (ObjectUtils.isNull(electionCandidate)) {
                throw new ParameterCheckException(Status.DATA_NOT_FOUND);
            }
            return electionCandidate.getCount();
        }
        return count;
    }

    @Override
    public List<ElectionCandidate> result(Long id) {
        Election election = queryById(id);
        if (ObjectUtils.isNull(election)) {
            throw new ParameterCheckException(Status.DATA_NOT_FOUND);
        }

        List<ElectionCandidate> electionCandidates = electionCandidateService.listByElectionId(id);
        Properties properties = new Properties();
        properties.setProperty("electionId", id.toString());
        for (ElectionCandidate electionCandidate : electionCandidates) {
            properties.setProperty("candidateId", electionCandidate.getCandidateId().toString());
            String key = StringUtils.replacePlaceholders(RedisConstant.Election.NUMBER_OF_VOTES, properties);
            Long result = redisString2LongTemplate.opsForValue().get(key);
            if (ObjectUtils.nonNull(result)) {
                electionCandidate.setCount(result);
            }
        }
        return electionCandidates;
    }

    private void finishPreCheck(Long id) {
        Election election = queryById(id);
        if (ObjectUtils.isNull(election)) {
            throw new ParameterCheckException(Status.DATA_NOT_FOUND);
        }

        if (!election.getStatus().equals(EElectionStatus.STARTED.getCode())) {
            throw new ParameterCheckException(Status.CURRENT_STATE_IS_NOT_OPERATIONAL);
        }
    }

    /**
     * 前置校验
     * @param dto 前端dtp参数
     */
    private void votePreCheck(VoteDTO dto) {

        // 校验用户是否存在
        User user = userService.queryById(dto.getUserid());
        if (ObjectUtils.isNull(user)) {
            throw new ParameterCheckException(Status.DATA_NOT_FOUND);
        }

        // 校验选举状态及数据
        Election election = queryById(dto.getElectionId());
        if (ObjectUtils.isNull(election)) {
            throw new ParameterCheckException(Status.DATA_NOT_FOUND);
        }

        if (!EElectionStatus.STARTED.getCode().equals(election.getStatus())) {
            throw new ParameterCheckException(Status.CURRENT_STATE_IS_NOT_OPERATIONAL);
        }

        // 校验候选人是否存在于选举
        ElectionCandidate electionCandidate = electionCandidateService.queryElectionByCandidateId(dto.getElectionId(), dto.getCandidateId());
        if (ObjectUtils.isNull(electionCandidate)) {
            throw new ParameterCheckException(Status.DATA_NOT_FOUND);
        }

        // 校验当前用户是否已经投票
        ElectionCandidateDetail electionCandidateDetail = electionCandidateDetailService.queryVoteByUserid(dto.getUserid(), dto.getElectionId());
        if (ObjectUtils.nonNull(electionCandidateDetail)) {
            throw new ParameterCheckException(Status.DUPLICATE_VOTE);
        }

    }
}
