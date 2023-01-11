package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.common.lang.http.Status;
import com.bnyte.election.api.dto.user.RegisterDTO;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.exception.ParameterCheckException;
import com.bnyte.election.api.exception.GlobalException;
import com.bnyte.election.api.mapper.UserMapper;
import com.bnyte.election.api.service.IUserService;
import com.bnyte.election.api.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *    用户业务服务实现
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User register(RegisterDTO dto) {

        User user = null;

        // 校验重复用户登记
        if (ObjectUtils.isNull(dto.getId()) || dto.getId() < 1) {
            user = queryWithEmailOrIdCard(dto.getEmail(), dto.getIdCard());
            if (ObjectUtils.nonNull(user)) {
                throw new ParameterCheckException(Status.EMAIL_OR_ID_CARD_EXISTS);
            }
        }

        // 组装登记用户信息
        user = buildSaveRegisterUser(dto);

        // 保存用户信息
        saveOrUpdateById(user);

        return user;
    }

    /**
     * 构建登记用户信息
     *  通过id识别是新增或者更新
     *  id小于0或为空时为新增 反之更新
     * @param dto 登记用户信息DTO
     * @return 需要保存的登记信息
     */
    private User buildSaveRegisterUser(RegisterDTO dto) {
        User user = queryById(dto.getId());
        if (ObjectUtils.isNull(user)) {
            user = new User();
            user.setCreateTime(new Date());
        }
        user.setModifiedTime(new Date());
        user.setAdmin(dto.getAdmin());
        user.setEmail(dto.getEmail());
        user.setIdCard(dto.getIdCard());
        user.setDeleted(false);
        return user;
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
        if (ObjectUtils.isNull(user.getId()) || user.getId() < 1) {
            return userMapper.insert(user);
        } else {
            return userMapper.updateById(user);
        }
    }

    @Override
    public User queryById(Serializable id) {
        return userMapper.selectById(id);
    }
}
