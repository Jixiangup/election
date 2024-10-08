package com.bnyte.election.api.mapper;

import com.bnyte.election.api.entity.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *     用户ORM
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface UserMapper {
    User queryWhetherTheUserExists(@Param("email") String email, @Param("idCard") String idCard);

    Long insert(@Param("item") User user);

    Long updateById(@Param("item") User user);

    User selectById(@Param("id") Serializable id);
}
