package com.vision4jpc.framework.exception;

import com.vision4jpc.common.entity.results.http.HttpResultPojo;
import com.vision4jpc.common.entity.results.http.HttpResultResp;
import com.vision4jpc.common.exceptions.base.BaseServiceExecption;
import com.vision4jpc.common.exceptions.system.SysServiceException;
import com.vision4jpc.common.utils.i18n.I18nUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.exception
 * @className: GlobalExceptionHandler
 * @author: WQL-KXJ
 * @description: TODO 全局异常处理(响应状态信息进行国际化处理)
 * @date: 2024/9/6 10:09
 * @version: v1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 定义日志记录器，用于记录异常信息
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理SysServiceException系统业务和ShootServiceException射击模块业务功能异常类型的异常
    @ExceptionHandler({SysServiceException.class})
    public HttpResultPojo handleServiceException(BaseServiceExecption e) {
        // 获取国际化消息
        String emessage = I18nUtils.getValueByKey(e.getMessage());
        // 记录异常的错误信息和堆栈跟踪日志
        log.error(emessage, e);
        // 简化警告状态码的判空逻辑(使用Optional来处理code是否为空，避免显式的null检查)
        return (HttpResultPojo) Optional.ofNullable(e.getCode())
                // 如果code不为空,则执行map操作。通过HttpResultResp.wran(code,emessage)创建包含警告代码和消息的响应
                .map(code -> HttpResultResp.wran(code, emessage))
                //如果code为空，则使用orElseGet返回只包含警告消息的响应
                .orElseGet(() -> HttpResultResp.wran(emessage));
    }
}
