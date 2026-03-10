package com.vision4jpc.common.entity.results.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.domain.results.http
 * @className: HttpResultPojo
 * @author: WQL-KXJ
 * @description: TODO HTTP响应数据封装实体类
 * @date: 2024/8/20 20:33
 * @version: v1.0
 */
public class HttpResultPojo<T> {
    //状态码
    Integer code;

    //消息
    String message;

    //数据
    T data;

    public HttpResultPojo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
