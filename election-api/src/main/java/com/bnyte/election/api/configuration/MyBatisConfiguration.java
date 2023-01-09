package com.bnyte.election.api.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Configuration
@MapperScan("com.bnyte.election.api.mapper")
public class MyBatisConfiguration {

    

}
