package com.bnyte.election.api.dto.candidate;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class EditorCandidateDTO {

    /**
     * 操作的用户id
     */
    private Long userid;

    /**
     * 新的候选人昵称
     */
    private String nickname;

    /**
     * 候选人id
     */
    private Long id;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

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
