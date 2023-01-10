package com.bnyte.election.api.controller;

import com.bnyte.election.api.common.annotation.aop.OperatingAuthority;
import com.bnyte.election.api.common.lang.http.R;
import com.bnyte.election.api.mapstruct.election.ElectionTransfer;
import com.bnyte.election.api.param.election.EditorElectionParam;
import com.bnyte.election.api.service.IElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
