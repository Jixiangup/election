package com.bnyte.election.api.controller;

import com.bnyte.election.api.common.lang.http.R;
import com.bnyte.election.api.mapstruct.candidate.CandidateTransfer;
import com.bnyte.election.api.param.candidate.EditorCandidateParam;
import com.bnyte.election.api.service.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bnyte
 * @since 1.0.0
 */
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private ICandidateService candidateService;

    /**
     * 编辑候选人
     *  id小于0或为空时则为新增 反之更新
     * @param payload 请求参数
     * @return 数据主键id
     */
    @PostMapping("/editor")
    public R<Long> editor(@RequestBody EditorCandidateParam payload) {
        return R.OK(candidateService.editor(CandidateTransfer.INSTANCE.param2DTO(payload)));
    }

}
