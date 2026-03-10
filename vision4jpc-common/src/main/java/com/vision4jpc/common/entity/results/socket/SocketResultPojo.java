package com.vision4jpc.common.entity.results.socket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.domain.results.socket
 * @className: SocketResultPojo
 * @author: WQL-KXJ
 * @description: TODO Socket响应数据封装实体类
 * @date: 2024/8/20 20:44
 * @version: v1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SocketResultPojo<T> {

    //推送消息类型
    Integer type;

    //消息
    String message;

    //数据
    T data;
}
