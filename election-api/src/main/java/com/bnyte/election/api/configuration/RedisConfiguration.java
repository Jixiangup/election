package com.bnyte.election.api.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * redis自动装配配置类
 * @author bnyte
 * @since 1.0.0
 */
@Configuration
public class RedisConfiguration {

    /*
        启动warn看: https://github.com/spring-projects/spring-framework/issues/29612
     */
    @Bean
    RedisTemplate<String, Long> redisString2LongTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(serializerLong());
        template.setKeySerializer(serializerString());
        return template;
    }

    private Jackson2JsonRedisSerializer<String> serializerString() {
        ObjectMapper objectMapper = new ObjectMapper();

        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        return new Jackson2JsonRedisSerializer<>(objectMapper, String.class);
    }

    private Jackson2JsonRedisSerializer<Long> serializerLong() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        return new Jackson2JsonRedisSerializer<>(objectMapper, Long.class);
    }
}
