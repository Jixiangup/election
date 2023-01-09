package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.common.lang.http.Status;
import com.bnyte.election.api.dto.user.RegisterDTO;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.exception.CheckException;
import com.bnyte.election.api.exception.GlobalException;
import com.bnyte.election.api.mapper.UserMapper;
import com.bnyte.election.api.service.IUserService;
import com.bnyte.election.api.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *    用户业务服务实现
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(RegisterDTO dto) {

        User user = queryWithEmailOrIdCard(dto.getEmail(), dto.getIdCard());

        // 校验重复用户登记
        if (ObjectUtils.nonNull(user)) {
            throw new CheckException(Status.EMAIL_OR_ID_CARD_EXISTS);
        }

        // 组装登记用户信息
        user = buildRegisterUser(dto);

        // 保存用户信息



        return null;
    }

    /**
     * 通过前端请求组装用户信息
     * @param dto 前端请求DTO
     * @return 组装之后的用户信息
     */
    private User buildRegisterUser(RegisterDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setIdCard(dto.getIdCard());
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        user.setDeleted(false);
        user.setAdmin(dto.getAdmin());
        return user;
    }

    @Override
    public User queryWithEmailOrIdCard(String email, String idCard) {
        return userMapper.queryWhetherTheUserExists(email, idCard);
    }

    @Override
    public Long saveOrUpdateById(User user) {
        if (ObjectUtils.isNull(user)) {
            throw new GlobalException(Status.SYSTEM_ERROR);
        }
        if (null == user.getId() || user.getId() < 1) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
        return user.getId();
    }
}
