package com.vision4jpc.system.eventbus.listener;
import com.vision4jpc.common.core.channel.NettyChannelManager;
import com.vision4jpc.common.entity.results.socket.SocketResultPojo;
import com.vision4jpc.common.entity.results.socket.SocketResultPush;
import com.vision4jpc.common.utils.cache.SysConfigCacheUtil;
import com.vision4jpc.common.utils.json.JsonUtil;
import com.vision4jpc.system.eventbus.event.ModuleEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.eventbus.listener
 * @className: ModuleListener
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/28 20:09
 * @version: v1.0
 */
@Component
public class ModuleListener {

    @EventListener
    public void handleModuleUpdateEvent(ModuleEvent moduleEvent) {
        //更新缓存模块信息
        SysConfigCacheUtil.setShootModuleInfo(moduleEvent.getModuleInfo());
        // 获取更新后的系统模块数据并封装
        SocketResultPojo<Map<String, String>> moduleMessageSocketResultPojo = SocketResultPush.sysModuleMessage(SysConfigCacheUtil.getShootModuleInfo());
        // 发送更新后的所有系统模块信息给当前在线的所有客户端
        NettyChannelManager.sendAllMessage(JsonUtil.toJsonString(moduleMessageSocketResultPojo));
    }
}
