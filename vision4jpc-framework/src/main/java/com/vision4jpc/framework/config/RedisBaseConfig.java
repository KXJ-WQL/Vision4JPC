package com.vision4jpc.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.config
 * @className: RedisConfig
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/14 10:39
 * @version: v1.0
 */

@Configuration
public class RedisBaseConfig {

    /**
     * 自定义 RedisTemplate，用于处理 Redis 数据库的操作。
     *  RedisTemplate 配置了自定义的序列化器，适用于存储和读取复杂的对象。
     *
     * @param redisConnectionFactory Redis 连接工厂，用于创建连接
     * @return RedisTemplate<Object, Object> 自定义的 RedisTemplate 实例
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        // 创建 RedisTemplate 实例
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        // 设置连接工厂，RedisTemplate 用于执行 Redis 操作的连接
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用 GenericJackson2JsonRedisSerializer 作为默认的 value 序列化器
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // 设置 key 的序列化器为 StringRedisSerializer，将 key 序列化为字符串
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // 设置 value 的序列化器为 GenericJackson2JsonRedisSerializer，将对象序列化为 JSON
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        // 返回配置好的 RedisTemplate 实例
        return redisTemplate;
    }

    /**
     * 自定义 StringRedisTemplate，用于处理纯字符串的 Redis 操作。
     * 与 RedisTemplate 不同，StringRedisTemplate 专门用于处理 key 和 value 都为字符串的场景。
     *
     * @param redisConnectionFactory Redis 连接工厂，用于创建连接
     * @return StringRedisTemplate 自定义的 StringRedisTemplate 实例
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        // 创建 StringRedisTemplate 实例
        StringRedisTemplate template = new StringRedisTemplate();

        // 设置连接工厂，StringRedisTemplate 用于执行 Redis 操作的连接
        template.setConnectionFactory(redisConnectionFactory);

        // 返回配置好的 StringRedisTemplate 实例
        return template;
    }
}
