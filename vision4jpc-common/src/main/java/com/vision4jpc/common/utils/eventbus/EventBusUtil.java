package com.vision4jpc.common.utils.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils.eventbus
 * @className: EventBusUtil
 * @author: WQL-KXJ
 * @description: 自定义事件发布工具类
 * @date: 2024/9/22 11:45
 * @version: v1.0
 */
@Component
public class EventBusUtil {
    // 静态变量，存储 Spring 的 ApplicationContext 实例
    private static ApplicationContext applicationContext;

    // 构造函数，通过依赖注入获取 ApplicationContext 实例
    @Autowired
    public EventBusUtil(ApplicationContext applicationContext) {
        EventBusUtil.applicationContext = applicationContext;
    }

    // 发布自定义事件的方法
    public static void publishEvent(Object event) {
        applicationContext.publishEvent(event);
    }
}
