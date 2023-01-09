package com.bnyte.election.api.dto.user;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class RegisterDTO {

    private String email;

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
