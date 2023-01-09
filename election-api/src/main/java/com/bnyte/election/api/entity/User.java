package com.bnyte.election.api.entity;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class User extends AbsAutoIdEntity {

    /**
     * 用户邮件
     */
    private String email;

    /**
     * 香港身份证
     */
    private String idCard;

    /**
     * 是否为管理员, 1/true 管理员身份 0/false 普通用户
     */
    private Boolean admin;

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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
