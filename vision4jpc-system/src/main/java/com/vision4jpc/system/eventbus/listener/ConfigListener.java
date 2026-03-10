package com.vision4jpc.system.eventbus.listener;

import com.vision4jpc.common.core.channel.NettyChannelManager;
import com.vision4jpc.common.entity.results.socket.SocketResultPojo;
import com.vision4jpc.common.entity.results.socket.SocketResultPush;
import com.vision4jpc.common.utils.cache.SysConfigCacheUtil;
import com.vision4jpc.common.utils.json.JsonUtil;
import com.vision4jpc.system.eventbus.event.ConfigEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.eventbus.listener
 * @className: ConfigListener
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/28 20:09
 * @version: v1.0
 */
@Component
public class ConfigListener {

    @EventListener
    public void handleConfigUpdateEvent(ConfigEvent configEvent) {
        // 更新缓存配置信息
        configEvent.getConfigInfo().forEach((key,value)-> SysConfigCacheUtil.setAllConfigurations(key,value));
        // 获取更新后的所有配置数据
        Map<String, String> allConfigurations = SysConfigCacheUtil.getAllConfigurations();
        // 手动添加一个实时时间搓(同步平板和总控的记录时间差, 方便日志排除问题)
        allConfigurations.put("synchronizeTimeRubbing", String.valueOf(System.currentTimeMillis()));
        // 封装系统配置数据
        SocketResultPojo<Map<String, String>> configSocketResultPojo = SocketResultPush.sysConfigMessage(allConfigurations);
        // 发送更新后的所有系统配置信息给当前在线的所有客户端(不需要补发 - 离线的重新连接会直接发送)
        NettyChannelManager.sendAllMessage(JsonUtil.toJsonString(configSocketResultPojo));
    }
}
