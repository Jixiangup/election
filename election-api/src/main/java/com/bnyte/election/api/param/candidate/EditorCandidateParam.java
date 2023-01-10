package com.bnyte.election.api.param.candidate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 *     编辑候选人请求参数
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class EditorCandidateParam {

    /**
     * 新的候选人昵称
     */
    @NotBlank(message = "候选人昵称不能为空")
    private String nickname;

    /**
     * 候选人id
     */
    private Long id;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
