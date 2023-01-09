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
     * 邮箱
     */
    @NotBlank(message = "邮箱地址不能为空")
    @Pattern(regexp = "^\\\\s*\\\\w+(?:\\\\.?[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$", message = "邮箱地址不合法")
    private String email;

    /**
     * 香港身份证
     */
    @NotBlank(message = "身份证不能为空")
    @Pattern(regexp = "^[A-Z]?[A-Z]\\\\d{6}\\\\([0-9A]{1}\\\\)$", message = "身份证不合法")
    private String idCard;

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
