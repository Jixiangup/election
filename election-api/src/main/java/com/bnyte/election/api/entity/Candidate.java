package com.bnyte.election.api.entity;

/**
 * 候选人数据库映射对象
 * @author bnyte
 * @since 1.0.0
 */
public class Candidate extends AbsAutoIdEntity {

    /**
     * 候选人昵称
     */
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
