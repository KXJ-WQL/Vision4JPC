package com.vision4jpc.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.config
 * @className: RedisMessageListenerConfig
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/10/7 19:31
 * @version: v1.0
 */
@Configuration
public class RedisMessageListenerConfig {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        // 创建Redis消息队列容器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);


        return container;
    }
}
