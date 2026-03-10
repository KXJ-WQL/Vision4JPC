package com.vision4jpc.framework.config;

import com.vision4jpc.framework.decorator.MDCTaskDecorator;
import com.vision4jpc.framework.exception.AsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.config
 * @className: ThreadPoolConfig
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/29 13:53
 * @version: v1.0
 */
@Configuration
@EnableAsync(proxyTargetClass = true) //启用Spring线程池多线程操作
public class ThreadPoolConfig implements AsyncConfigurer {

    @Autowired
    AsyncExceptionHandler asyncExceptionHandler;

    //定义射击进行中的处理线程池
    @Bean("shootOngoingThreadPool")
    public Executor shootOngoingThreadPool(){
        //Spring内部提供的线程池
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        //1.核心线程数：线程池创建时候初始化的线程数
        threadPoolTaskExecutor.setCorePoolSize(20);

        //2.最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        threadPoolTaskExecutor.setMaxPoolSize(24);

        //3.缓存队列：用来缓冲执行任务的队列
        threadPoolTaskExecutor.setQueueCapacity(500);

        //4.允许线程存活的时间(s)：当超过核心线程之外的线程在空闲时间到达之后会被销毁
        threadPoolTaskExecutor.setKeepAliveSeconds(10);

        //5.缓冲队列满之后的拒绝策略：由调用线程处理(一般主线程)
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        //6.设置MDC链路追踪日志装饰器(用于线程间传递MDC上下文将不同靶位处理的日志区别保存)
        threadPoolTaskExecutor.setTaskDecorator(new MDCTaskDecorator());

        //线程池名的前缀：方便定位处理任务所在的线程池
        threadPoolTaskExecutor.setThreadNamePrefix("shootOngoingThreadPool");

        //线程池初始化
        threadPoolTaskExecutor.initialize();
        return  threadPoolTaskExecutor;
    }


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        // 返回多线程异步业务异常处理类
        return asyncExceptionHandler;
    }
}
