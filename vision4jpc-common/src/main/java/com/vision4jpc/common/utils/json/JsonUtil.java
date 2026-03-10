package com.vision4jpc.common.utils.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils.json
 * @className: JsonUtil
 * @author: WQL-KXJ
 * @description: TODO 封装Json工具类
 * @date: 2024/9/24 14:26
 * @version: v1.0
 */
public class JsonUtil {

    // 将对象转换为 JSON 字符串
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    // 将 JSON 字符串转换为指定类型的对象
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    // 从 Map 转换为指定类型的对象
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        String jsonString = toJsonString(fromValue);
        return fromJsonString(jsonString, toValueType);
    }

    // 支持泛型的反序列化方法
    public static <T> T fromJsonString(String jsonString, TypeReference<T> typeReference) {
        return JSON.parseObject(jsonString, typeReference);
    }

}
