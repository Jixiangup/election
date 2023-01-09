package com.bnyte.election.api.param.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * <p>
 *     登记用户请求参数
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class RegisterParam {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱地址不能为空")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱地址不合法")
    private String email;

    /**
     * 香港身份证
     */
    @NotBlank(message = "身份证不能为空")
    @Pattern(regexp = "^[A-Z]?[A-Z]\\d{6}\\([0-9A]\\)$", message = "身份证不合法")
    private String idCard;

    /**
     * 是否为管理员
     */
    private Boolean admin = false;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
