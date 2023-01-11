package com.bnyte.election.api.http.interceptor;

import com.bnyte.election.api.common.constant.WebConstant;
import com.bnyte.election.api.common.util.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *     请求拦截器
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = request.getHeader(WebConstant.HeaderConstant.REQUEST_ID);
        boolean hasRequestId = StringUtils.hasText(requestId);
        if (!hasRequestId) {
            requestId = RequestUtils.generateRequestId();
        }
        response.addHeader(WebConstant.HeaderConstant.REQUEST_ID, requestId);
        response.addHeader(WebConstant.HeaderConstant.REQUEST_TIMESTAMP, String.valueOf(System.currentTimeMillis()));

        // 添加日志请求id输出
        MDC.put("RequestId", requestId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        MDC.clear();
    }
}
