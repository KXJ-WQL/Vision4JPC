package com.vision4jpc.common.exceptions.system;


import com.vision4jpc.common.exceptions.base.BaseServiceExecption;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.exceptions.system
 * @className: SysServiceException
 * @author: WQL-KXJ
 * @description: TODO 系统业务逻辑异常
 * @date: 2024/9/6 11:26
 * @version: v1.0
 */
public class SysServiceException extends BaseServiceExecption {

    // 空构造函数
    public SysServiceException() {}

    public SysServiceException(String message) {
        super(message);
    }

    public SysServiceException(String message, Integer code) {
        super(message, code);
    }
}
