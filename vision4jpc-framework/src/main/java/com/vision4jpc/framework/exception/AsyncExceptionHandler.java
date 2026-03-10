package com.vision4jpc.framework.exception;

import com.vision4jpc.common.utils.eventbus.EventBusUtil;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.exception
 * @className: AsyncExceptionHandler
 * @author: WQL-KXJ
 * @description: TODO Async异步任务异常统一处理
 * @date: 2024/11/10 11:42
 * @version: v1.0
 */
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
         // 通过事件机制异步传递给监听处理器进行处理
//         EventBusUtil.publishEvent(SocketResultPush.shootExceptionMessage(ex));
    }
}
