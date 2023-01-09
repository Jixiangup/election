package com.bnyte.election.api.controller;

import com.bnyte.election.api.common.lang.http.R;
import com.bnyte.election.api.mapstruct.user.UserTransfer;
import com.bnyte.election.api.param.user.RegisterParam;
import com.bnyte.election.api.service.IUserService;
import com.bnyte.election.api.vo.user.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     用户控制器
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public R<RegisterVO> register(@RequestBody @Validated RegisterParam payload) {
        return R.OK(UserTransfer.INSTANCE.entity2VO(userService.register(UserTransfer.INSTANCE.param2DTO(payload))));
    }



}
