package com.bnyte.election.api.service;

import com.bnyte.election.api.dto.user.RegisterDTO;
import com.bnyte.election.api.entity.User;

import java.io.Serializable;

/**
 * <p>
 *     用户业务服务抽象
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface IUserService {

    /**
     * 登记一名新的用户
     * @param dto 登记用户的DTO
     * @return 登记的用户信息
     */
    User register(RegisterDTO dto);

    /**
     * 通过邮件或身份证获取用户
     * @param email 邮件
     * @param idCard 身份证
     * @return 用户信息
     */
    User queryWithEmailOrIdCard(String email, String idCard);

    /**
     * 通过主键ID保存或者更新用户信息
     *  id < 0 或为空则新增, 否则通过id更新
     * @param user 用户信息
     * @return 主键id
     */
    Long saveOrUpdateById(User user);

    /**
     * 通过主键查询用户
     * @param id 主键id
     * @return 用户信息
     */
    User queryById(Serializable id);
}
