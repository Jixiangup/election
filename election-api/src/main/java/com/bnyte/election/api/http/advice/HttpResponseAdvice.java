package com.bnyte.election.api.http.advice;

import com.bnyte.election.api.common.constant.WebConstant;
import com.bnyte.election.api.common.lang.http.R;
import com.bnyte.election.api.common.lang.http.Status;
import com.bnyte.election.api.exception.CheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * <p>
 *     响应主体处理器
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@RestControllerAdvice("com.bnyte.election.api.controller")
public class HttpResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(HttpResponseAdvice.class);

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(CheckException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Void> handlerAuthenticationException(CheckException e) {
        if (log.isErrorEnabled()) {
            log.error(e.getStatus().getMessage(), e);
        }
        return R.ERROR(e.getStatus());
    }

    /**
     * 参数检验异常处理
     * @param e 异常响应
     * @return 响应结果
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    R<Void> handleValidationException(BindException e) {
        if (log.isErrorEnabled()) {
            log.error("[Validation Check Exception] because {}", e.getMessage(), e);
        }
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringJoiner message = new StringJoiner(",");
        fieldErrors.forEach(error -> message.add(error.getDefaultMessage()));
        return R.ERROR(Status.PAYLOAD_ASSERT_ERROR, message.toString());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String requestId = Objects.requireNonNull(response.getHeaders().get(WebConstant.HeaderConstant.REQUEST_ID)).get(0);
        String requestTimestamp = Objects.requireNonNull(response.getHeaders().get(WebConstant.HeaderConstant.REQUEST_TIMESTAMP)).get(0);
        // 添加请求时间和请求id响应重写
        if (body instanceof R<?> r) {
            r.setRequestId(requestId);
            r.setTimestamp(Long.parseLong(requestTimestamp));
            return r;
        }
        return body;
    }
}
