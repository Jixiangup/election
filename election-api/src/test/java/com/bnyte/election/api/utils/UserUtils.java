package com.bnyte.election.api.utils;

import com.bnyte.election.api.dto.user.RegisterDTO;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.service.IUserService;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class UserUtils {

    public static Long adminUserid = -1L;

    /**
     * 登记一个管理员用户
     * @param userService 用户服务IOC对象
     * @return 用户主键ID
     */
    public static Long registerAdmin(IUserService userService) {
        if (adminUserid == -1) {
            RegisterDTO dto = new RegisterDTO();
            dto.setAdmin(true);
            dto.setEmail("bnytezz@gmail.com");
            dto.setIdCard("A000000(0)");
            User register = userService.register(dto);
            adminUserid = register.getId();
        }
        return adminUserid;
    }
}
