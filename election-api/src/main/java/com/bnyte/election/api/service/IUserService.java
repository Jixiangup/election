package com.bnyte.election.api.service;

import com.bnyte.election.api.dto.user.RegisterDTO;
import com.bnyte.election.api.entity.User;

/**
 * <p>
 *     用户业务服务抽象
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface IUserService {

    User register(RegisterDTO dto);

    User queryWithEmailOrIdCard(String email, String idCard);
}
