package com.bnyte.election.api.mapstruct.user;

import com.bnyte.election.api.dto.user.RegisterDTO;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.param.user.RegisterParam;
import com.bnyte.election.api.vo.user.RegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 *     t_user数据对象转换器
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface UserTransfer {

    UserTransfer INSTANCE = Mappers.getMapper(UserTransfer.class);

    RegisterDTO toDTO(RegisterParam param);


    RegisterVO toVO(User user);
}
