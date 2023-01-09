package com.bnyte.election.api.mapper;

import com.bnyte.election.api.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *     用户ORM
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public interface UserMapper {
    User queryWhetherTheUserExists(@Param("email") String email, @Param("idCard") String idCard);
}
