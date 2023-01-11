package com.bnyte.election.api.controller;

import com.bnyte.election.api.annotation.aop.DistributedLock;
import com.bnyte.election.api.annotation.aop.OperatingAuthority;
import com.bnyte.election.api.annotation.aop.Property;
import com.bnyte.election.api.common.constant.RedisConstant;
import com.bnyte.election.api.common.enums.EDistributedLockType;
import com.bnyte.election.api.common.lang.http.R;
import com.bnyte.election.api.mapstruct.election.ElectionTransfer;
import com.bnyte.election.api.param.election.EditorElectionParam;
import com.bnyte.election.api.param.election.VoteParam;
import com.bnyte.election.api.service.IElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
