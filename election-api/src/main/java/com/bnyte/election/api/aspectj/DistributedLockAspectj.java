package com.bnyte.election.api.aspectj;

import com.bnyte.election.api.annotation.aop.DistributedLock;
import com.bnyte.election.api.annotation.aop.Property;
import com.bnyte.election.api.common.constant.RedisConstant;
import com.bnyte.election.api.common.lang.http.Status;
import com.bnyte.election.api.common.util.ObjectUtils;
import com.bnyte.election.api.common.util.StringUtils;
import com.bnyte.election.api.exception.GlobalException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 分布式锁注解实现
 * @author bnyte
 * @since 1.0.0
 */
@Component
@Aspect
public class DistributedLockAspectj {

    @Autowired
    RedisTemplate<String, String> redisStringTemplate;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.bnyte.election.api.annotation.aop.DistributedLock)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object aroundHandler(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        // get properties
        Properties properties = getTemplateProperties(method.getParameters(), point.getArgs());
        String lockKey = StringUtils.replacePlaceholders(distributedLock.lockKey(), properties);

        return switch (distributedLock.lockType()) {
            case REDIS -> lockOfRedis(lockKey, point);
            default -> throw new GlobalException(Status.UNSUPPORTED_DISTRIBUTED_LOCK_TYPE);
        };

    }

    private Properties getTemplateProperties(Parameter[] parameters, Object[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Properties properties = new Properties();
        if (ObjectUtils.isNull(parameters) || parameters.length < 1) {
            return properties;
        }
        int index = 0;
        for (Parameter parameter : parameters) {
            if (ObjectUtils.isNull(parameter)) {
                continue;
            }
            Property property = parameter.getAnnotation(Property.class);
            if (ObjectUtils.isNull(property)) {
                continue;
            } else {
                Class<?> parameterType = parameter.getType();
                for (Field field : parameterType.getDeclaredFields()) {
                    String key = field.getName();
                    Object arg = args[index];
                    PropertyDescriptor beanProperty = new PropertyDescriptor(key, arg.getClass());
                    String value = beanProperty.getReadMethod().invoke(arg).toString();

                    properties.put(key, value);
                }
            }
            index++;
        }
        return properties;
    }

    /**
     * 使用redis加锁并执行业务
     * @param lockKey 锁的key
     * @param point 被执行的代理切入点
     * @return 返回被执行目标方法返回值
     * @throws Throwable 所有异常
     */
    private Object lockOfRedis(String lockKey, ProceedingJoinPoint point) throws Throwable {
        String lockValue = UUID.randomUUID().toString();
        try {
            Boolean lock = redisStringTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 3, TimeUnit.SECONDS);
            if (Boolean.TRUE.equals(lock)) {
                // 获得锁
                Object result = point.proceed();
                redisStringTemplate.execute(new DefaultRedisScript<>(RedisConstant.Lock.UNLOCK_SCRIPT, Long.class), Stream.of(lockKey).toList(), lockValue);
                return result;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return lockOfRedis(lockKey, point);
            }
        } finally {
            redisStringTemplate.execute(new DefaultRedisScript<>(RedisConstant.Lock.UNLOCK_SCRIPT, Long.class), Stream.of(lockKey).toList(), lockValue);
        }
    }

}
