package com.vision4jpc.common.exceptions.base;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.exceptions.base
 * @className: BaseExecption
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/14 12:15
 * @version: v1.0
 */
public class BaseServiceExecption extends RuntimeException{
    // 异常错误码
    private Integer code;

    // 异常错误信息
    private String message;

    // 异常详细信息（用于开发者调试）
    private String detailMessage;

    // 空构造函数
    public BaseServiceExecption() {}

    public BaseServiceExecption(String message) {
        this.message = message;
    }

    public BaseServiceExecption(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
