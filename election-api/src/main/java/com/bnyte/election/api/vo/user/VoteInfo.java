package com.bnyte.election.api.vo.user;

/**
 * 投票用户详情回显
 * @author bnyte
 * @since 1.0.0
 */
public class VoteInfo {

    /**
     * 投票用户id
     */
    private Long userid;

    /**
     * 投票用户邮箱
     */
    private String email;

    /**
     * 投票用户身份证号码
     */
    private String idCard;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
