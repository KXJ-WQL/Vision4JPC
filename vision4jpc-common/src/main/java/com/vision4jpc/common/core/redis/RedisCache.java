package com.vision4jpc.common.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.shootinglivecore.redis
 * @className: RedisCache
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/20 20:22
 * @version: v1.0
 */
@Component
public class RedisCache {

    public static RedisTemplate redisTemplate;

    @Autowired
    public RedisCache(RedisTemplate redisTemplate) {
        RedisCache.redisTemplate = redisTemplate;
    }

    // 缓存基本的对象，Integer、String、实体类等
    public static  <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    //缓存超时基本的对象，Integer、String、实体类等
    public static <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    // 发布字符串类型的消息给消息队列
    public static void publish(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }

    // 发布对象类型的消息给消息队列
    public static void publish(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }

    // 判断 key是否存在
    public static Boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }

    // 获得缓存的基本对象。
    public static <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    // 删除单个对象
    public static boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    // 删除集合对象
    public static boolean deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection) > 0;
    }

    // 缓存对象到list
    public static <T> long setObjectToLost(final String key, final T value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    // 缓存对象集合到List数据
    public static <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    // 获得缓存的list对象
    public static <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    // 获取并删除缓存列表的最后一条数据
    public static <T> T getLastCacheListItem(final String key) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    // 覆盖保存缓存的List数据
    public static <T> long overwriteCacheList(final String key, final List<T> dataList) {
        redisTemplate.delete(key);
        return setCacheList(key, dataList);
    }

    // 删除所有以前缀开头的键
    public static void deletePrefixAllKey(final String prefix) {
        Set<String> keys = redisTemplate.keys(String.format("%s:*",prefix));
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    //缓存Set
    public static <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    //获得缓存的set
    public static <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    //缓存Map
    public static <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    // 获得缓存的Map
    public static <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    // 往Hash中存入数据
    public static <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    // 获取Hash中的数据
    public static <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    //获取所有Hash中的数据
    public static <T> Map<String, T> getAllCacheMapValues(String key) {
        return redisTemplate.<String, T>opsForHash().entries(key);
    }

    // 删除Hash中的某条数据
    public static boolean deleteCacheMapValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    //获得缓存的基本对象列表
    public static Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
