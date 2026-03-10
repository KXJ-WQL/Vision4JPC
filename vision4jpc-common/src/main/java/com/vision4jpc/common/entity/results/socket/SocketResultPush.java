package com.vision4jpc.common.entity.results.socket;


import com.vision4jpc.common.constant.SocketStatus;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.domain.results.socket
 * @className: SocketResultPush
 * @author: WQL-KXJ
 * @description: TODO Socket响应数据封装方法类
 * @date: 2024/8/20 20:45
 * @version: v1.0
 */
public class SocketResultPush {

    public static <T> SocketResultPojo<T> systemMessage(String message, T data){return new SocketResultPojo(1,message,data);};

    public static <T> SocketResultPojo<T> systemMessage(T data){return new SocketResultPojo(1,"",data);};

    public static <T> SocketResultPojo<T> shootsMessage(T data){return new SocketResultPojo(2,"",data);};

    //系统模块数据
    public static <T> SocketResultPojo<T> sysModuleMessage( T data){return new SocketResultPojo(SocketStatus.SYSMODULEINFO,"系统功能模块数据",data);};

    //系统配置数据
    public static <T> SocketResultPojo<T> sysConfigMessage( T data){return new SocketResultPojo(SocketStatus.SYSCONFIGINFO,"系统配置数据",data);};

}
