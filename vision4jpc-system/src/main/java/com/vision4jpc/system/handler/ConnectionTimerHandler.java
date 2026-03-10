package com.vision4jpc.system.handler;

import com.vision4jpc.common.core.channel.NettyChannelManager;
import com.vision4jpc.common.entity.results.socket.SocketResultPojo;
import com.vision4jpc.common.entity.results.socket.SocketResultPush;
import com.vision4jpc.common.utils.cache.SysConfigCacheUtil;
import com.vision4jpc.common.utils.json.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.handler.connect
 * @className: ConnectionTimerHandler
 * @author: WQL-KXJ
 * @description: TODO 连接定时任务信息发送
 * @date: 2024/9/29 9:10
 * @version: v1.0
 */
public class ConnectionTimerHandler {

    private static final Logger log = LoggerFactory.getLogger(ConnectionTimerHandler.class);

    private final ChannelHandlerContext ctx;
    private final String clientIp;

    public ConnectionTimerHandler(ChannelHandlerContext ctx, String clientIp) {
        this.ctx = ctx;
        this.clientIp = clientIp;
    }

    // 发送系统模块信息
    public void scheduleModuleInfo(long delay, TimeUnit unit) {
        ctx.executor().schedule(() -> {
            // 获取系统模块数据并封装
            SocketResultPojo<Map<String, String>> moduleMessageSocketResultPojo = SocketResultPush.sysModuleMessage(SysConfigCacheUtil.getShootModuleInfo());
            JsonUtil.toJsonString(JsonUtil.toJsonString(moduleMessageSocketResultPojo));
            // 发送模块信息
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJsonString(moduleMessageSocketResultPojo)));
        }, delay, unit);
    }

    // 发送系统配置信息
    public void scheduleConfigInfo(long delay, TimeUnit unit) {
        ctx.executor().schedule(() -> {
            // 获取更新后的所有配置数据
            Map<String, String> allConfigurations = SysConfigCacheUtil.getAllConfigurations();
            // 封装系统配置数据
            SocketResultPojo<Map<String, String>> configSocketResultPojo = SocketResultPush.sysConfigMessage(allConfigurations);
            // 发送系统配置信息
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJsonString(configSocketResultPojo)));}, delay, unit);
    }


    // 延迟执行通道断开处理
    public void scheduleInactiveCleanup(ClientMessageHandler clientMessageHandler,long delay, TimeUnit unit) {
        ctx.executor().schedule(() -> {
            // 移除客户端
            NettyChannelManager.removeChannel(clientIp);
            // 离线消息处理
            clientMessageHandler.handlerClientOfflineMessage(clientIp);
            // 关闭通道
            ctx.channel().close();
            log.error("客户端 {} 断开连接！", clientIp);
        }, delay, unit);
    }

}
