package com.vision4jpc.system.eventbus.listener;
import com.vision4jpc.common.entity.results.socket.SocketResultPojo;
import com.vision4jpc.common.exceptions.system.SysServiceException;
import com.vision4jpc.common.utils.i18n.I18nUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.eventbus.listener
 * @className: ExceptionListener
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/11/14 14:09
 * @version: v1.0
 */
@Slf4j
@Component
public class ExceptionListener {

    @EventListener
    public void handleExceptionEvent(SocketResultPojo socketResultPojo) {
//        Object ex = socketResultPojo.getData();
//        log.info("接收到异常信息：" + socketResultPojo.toString());
//        if (ex instanceof SysServiceException) {
//            SysServiceException shootingLiveException = (SysServiceException) ex;
//            // 构建国际化异常消息，携带参数时进行拼接
//            String shootMessage =String.format(I18nUtils.getValueByKey(shootingLiveException.getMessage()),
//                    Optional.ofNullable(SysServiceException.getParameterValues()).orElse(""));
//            // 异常信息跟踪
//            log.error("异常事件", shootingLiveException);
//            System.out.println(shootMessage);
        }
    }

