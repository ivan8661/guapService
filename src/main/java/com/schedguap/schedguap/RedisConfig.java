package com.schedguap.schedguap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public <T>RedisTemplate<String, T> redisTemplate(RedisConnectionFactory factory) {
            RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(factory);
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }
}