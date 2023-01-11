package com.bnyte.election.api.aspectj;

import com.bnyte.election.api.common.constant.WebConstant;
import com.bnyte.election.api.common.util.ObjectUtils;
import com.bnyte.election.api.common.util.StringUtils;
import com.bnyte.election.api.entity.User;
import com.bnyte.election.api.exception.AuthException;
import com.bnyte.election.api.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 验证用户是否拥有操作权限AOP实现
 * @author bnyte
 * @since 1.0.0
 */
@Aspect
@Component
public class OperatingAuthorityAspectj {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    IUserService userService;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.bnyte.election.api.annotation.aop.OperatingAuthority)")
    public void pointcut(){}

    @Before("pointcut()")
    public void beforeAdvice() {
        String userid = request.getHeader(WebConstant.HeaderConstant.AUTH_KEY);
        if (StringUtils.nonText(userid)) {
            // 校验操作用户是否有权限
            throw new AuthException();
        }
        // 校验操作用户是否有权限
        User user = userService.queryById(userid);
        if (ObjectUtils.isNull(user) || !user.getAdmin()) {
            throw new AuthException();
        }
    }

}
