package com.vision4jpc.framework.decorator;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.decorator
 * @className: MDCTaskDecorator
 * @author: WQL-KXJ
 * @description: TODO MDC靶位异常处理任务MDC链路追踪装饰器
 * @date: 2024/12/10 12:12
 * @version: v1.0
 */
public class MDCTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable task) {
        // 获取当前父线程的 MDC 上下文 (关于var: 在Java10中引入了var关键字，它允许编译器根据右侧表达式的类型推断出变量的类型)
        var contextMap = MDC.getCopyOfContextMap();

        // 返回一个新的 Runnable，包装了原始任务 task
        return () -> {
            // MDC不为null, 将 MDC 上下文设置到当前子线程
            if (contextMap != null) MDC.setContextMap(contextMap);

            // 执行任务, 任务执行结束清理 MDC，避免影响其他线程
            try {task.run();} finally {MDC.clear();}
        };
    }
}
