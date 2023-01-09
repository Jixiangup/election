package com.bnyte.election.api.vo.user;

/**
 * <p>
 *     登记成功之后响应结果
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class RegisterVO {
    private Long id;

    private String email;

    private String idCard;

    private Boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
