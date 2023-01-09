package com.bnyte.election.api.service.impl;

import com.bnyte.election.api.dto.user.RegisterDTO;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.mapper.UserMapper;
import com.bnyte.election.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        return null;
    }

    @Override
    public User queryWithEmailOrIdCard(String email, String idCard) {
        return userMapper.queryWhetherTheUserExists(email, idCard);
    }
}
