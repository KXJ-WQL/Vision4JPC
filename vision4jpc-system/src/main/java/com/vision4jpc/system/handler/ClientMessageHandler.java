package com.vision4jpc.system.handler;

import com.alibaba.fastjson2.TypeReference;
import com.vision4jpc.common.core.redis.RedisCache;
import com.vision4jpc.common.entity.results.socket.SocketResultPojo;
import com.vision4jpc.common.utils.cache.SysConfigCacheUtil;
import com.vision4jpc.common.utils.json.JsonUtil;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.handler
 * @className: MessageHandler
 * @author: WQL-KXJ
 * @description: TODO 客户端信息处理
 * @date: 2025/6/19 10:50
 * @version: v1.0
 */
public class ClientMessageHandler {

    // 处理客户端端正常状态的消息
    public void handlerClientNormalMessage(String clientIP, String websocketJson){

        // 转换为SocketResultPojo对象结构
        SocketResultPojo socketResultPojo =  JsonUtil.fromJsonString(websocketJson, new TypeReference<SocketResultPojo<String>>() {});


    }

    // 处理客户端端离线状态的消息
    public void handlerClientOfflineMessage(String clientIP){


    }

}
