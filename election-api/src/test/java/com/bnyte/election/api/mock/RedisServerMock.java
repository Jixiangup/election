package com.bnyte.election.api.mock;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Configuration
public class RedisServerMock {

    private RedisServer redisServer;

    /**
     * 构造方法之后执行.
     *
     */
    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    /**
     * 析构方法之后执行.
     */
    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }

}
