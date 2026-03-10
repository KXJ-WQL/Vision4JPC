package com.vision4jpc.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils
 * @className: NullUtils
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/6 12:25
 * @version: v1.0
 */
public class NullUtils {

    // 判断一个对象是否为空
    public static boolean isNull(Object object) {
        return object == null;
    }

    // 判断一个对象是否非空
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    // 判断一个Collection是否为空，包含List，Set，Queue
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    // 判断一个Collection是否非空，包含List，Set，Queue
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    //判断一个Map是否为空
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    // 判断一个Map是否非空
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    // 判断一个String是否非空
    public static boolean isNotEmpty(String str) {
        return !isNull(str) && !str.isEmpty();
    }

    // 判断一个String是否为空
    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }
}
