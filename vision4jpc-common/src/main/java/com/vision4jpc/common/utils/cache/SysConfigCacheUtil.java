package com.vision4jpc.common.utils.cache;


import com.vision4jpc.common.core.redis.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils.config
 * @className: SysConfigUtil
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/28 21:15
 * @version: v1.0
 */
public class SysConfigCacheUtil {

    // 获取模块信息
    public static Map<String, String> getShootModuleInfo(){
        List<Map<String, String>> cacheList = RedisCache.getCacheList("sys_module:module.info");

        return cacheList.stream()
                .flatMap(map -> map.entrySet().stream()) // 将每个 Map 的 entrySet 转换为流
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing // 处理键冲突时，保留第一个值
                ));
    }

    // 设置模块信息
    public static void setShootModuleInfo(List<Map<String, String>> moduleInfo){
        RedisCache.overwriteCacheList("sys_module:module.info", moduleInfo);
    }

    //设置所有系统配置项
    public static void setAllConfigurations(String key,String value) {
        RedisCache.setCacheObject("sys_config:"+key,value);
    }

    // 获取所有系统配置项(做转化用于发送给客户端)
    public static Map<String, String> getAllConfigurations() {
        Set<String> keys = (Set<String>) RedisCache.keys("sys_config:*"); // 获取所有相关的键
        return keys.stream()
                .collect(Collectors.toMap(
                        key -> key.replace("sys_config:", ""), // 去掉前缀
                        RedisCache::getCacheObject // 获取对应的值
                ));
    }
}