package com.bnyte.election.api.controller;

import com.bnyte.election.api.annotation.aop.DistributedLock;
import com.bnyte.election.api.annotation.aop.OperatingAuthority;
import com.bnyte.election.api.annotation.aop.Property;
import com.bnyte.election.api.common.constant.RedisConstant;
import com.bnyte.election.api.common.enums.EDistributedLockType;
import com.bnyte.election.api.common.lang.http.Page;
import com.bnyte.election.api.common.lang.http.R;
import com.bnyte.election.api.mapstruct.election.ElectionTransfer;
import com.bnyte.election.api.param.election.EditorElectionParam;
import com.bnyte.election.api.param.election.VoteDetailSearch;
import com.bnyte.election.api.param.election.VoteParam;
import com.bnyte.election.api.service.IElectionService;
import com.bnyte.election.api.vo.election.ElectionResultVO;
import com.bnyte.election.api.vo.user.VoteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *     选举前端控制器
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@RestController
@RequestMapping("/election")
public class ElectionController {

    @Autowired
    IElectionService electionService;

    /**
     * 编辑选举信息
     * @param payload 请求参数
     * @return 选举主键id
     */
    @OperatingAuthority
    @PostMapping("/editor")
    public R<Long> editor(@RequestBody @Validated EditorElectionParam payload) {
        return R.OK(electionService.editor(ElectionTransfer.INSTANCE.payload2EditorDTO(payload)));
    }

    /**
     * 开始选举
     * @param id 选举id
     * @return 业务执行结果
     */
    @OperatingAuthority
    @GetMapping("/start")
    public R<Void> start(@RequestParam("id") Long id) {
        electionService.start(id);
        return R.EMPTY();
    }

    /**
     * 完成选举
     * @param id 选举id
     * @return 业务执行结果
     */
    @OperatingAuthority
    @GetMapping("/finish")
    public R<Void> finish(@RequestParam("id") Long id) {
        electionService.finish(id);
        return R.EMPTY();
    }

    /**
     * 发起一次投票
     * @param payload 投票请求参数
     * @return 业务执行结果
     */
    @PostMapping("/vote")
    @DistributedLock(lockKey = RedisConstant.Election.ELECTION_VOTE, lockType = EDistributedLockType.REDIS)
    public R<Void> vote(@RequestBody @Validated @Property VoteParam payload) {
        electionService.vote(ElectionTransfer.INSTANCE.payload2VoteDTO(payload));
        return R.EMPTY();
    }

    /**
     * 分页获取投票详情
     * @param search 分页参数
     * @return 返回分页数据
     */
    @PostMapping("/page/vote_detail")
    public R<Page<VoteInfo>> voteDetailOfPage(@RequestBody VoteDetailSearch search) {
        return R.OK(electionService.pageVoteDetail(search));
    }

    /**
     * 获取候选人的投票总数
     * @param electionId 选举id
     * @param candidateId 候选人id
     * @return 响应结果
     */
    @OperatingAuthority
    @GetMapping("/vote/candidate/count")
    public R<Long> candidateVoteCount(@RequestParam("election_id") Long electionId, @RequestParam("candidate_id") Long candidateId) {
        return R.OK(electionService.candidateVoteCount(electionId, candidateId));
    }

    /**
     * 获取选举结果
     * @param id 选举id
     * @return 返回当前选举的每个候选人的选取结果
     */
    @OperatingAuthority
    @GetMapping("/result")
    public R<List<ElectionResultVO>> result(@RequestParam("id") Long id) {
        return R.OK(ElectionTransfer.INSTANCE.toResultVO(electionService.result(id)));
    }

}
