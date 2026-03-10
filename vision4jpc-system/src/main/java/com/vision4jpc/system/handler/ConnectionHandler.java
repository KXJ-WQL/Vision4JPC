package com.vision4jpc.system.handler;

import com.vision4jpc.common.core.channel.NettyChannelManager;
import com.vision4jpc.common.utils.StringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.shooting.handler
 * @className: ConnectionHandler
 * @author: WQL-KXJ
 * @description: TODO websocket连接处理对象
 * @date: 2024/9/22 22:13
 * @version: v1.0
 */
public class ConnectionHandler {
    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    // 客户端消息处理类
    private final ClientMessageHandler clientMessageHandler;

    // 保存当前连接的上下文对象
    private final ChannelHandlerContext ctx;

    // 记录当前连接的客户端 IP 地址
    private final String clientIp;

    // 构造函数,初始化 ConnectionHandler 时，获取客户端的 IP 地址并保存
    public ConnectionHandler(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        // 获取客户端的远程地址，并提取其 IP 地址
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        this.clientIp = socketAddress.getAddress().getHostAddress();
        this.clientMessageHandler = new ClientMessageHandler();
    }

    // 处理初次连接时的逻辑
    public void handleInitialConnection() {
        // 将当前连接的通道加入到 ChannelManager 管理的通道集合中
        NettyChannelManager.addChannel(clientIp, ctx.channel());
        // 创建任务延迟定时处理类
        ConnectionTimerHandler timerHandler = new ConnectionTimerHandler(ctx, clientIp);
        // 延迟500ms发送模块信息
        timerHandler.scheduleModuleInfo(500, TimeUnit.MILLISECONDS);
        // 延迟1000ms发送配置信息
        timerHandler.scheduleConfigInfo(800, TimeUnit.MILLISECONDS);
        log.error("平板客户端新连接：" + ctx.channel().id().asLongText() + " 地址：" + clientIp);
    }

    // 处理客户端的信息
    public void handleChannelMessage(WebSocketFrame frame) {
        // 本地回路地址不进行消息处理(前端的消息进行过滤)
        if (StringUtils.isLocalAddress(clientIp)) return;
        // 进行具体消息处理
        clientMessageHandler.handlerClientNormalMessage(clientIp, ((TextWebSocketFrame) frame).text());
    }

    //处理通道变为活跃时的逻辑, 在通道变为活跃时补发可能丢失的消息
    public void handleChannelActive() {
        ConnectionTimerHandler timerManager = new ConnectionTimerHandler(ctx, clientIp);
    }

    // 处理通道断开时的逻辑, 这里可以添加清理资源、通知其他系统等逻辑
    public void handleChannelInactive() {
        ConnectionTimerHandler timerManager = new ConnectionTimerHandler(ctx, clientIp);
        // 延迟执行通道断开处理
        timerManager.scheduleInactiveCleanup(clientMessageHandler,500, TimeUnit.MILLISECONDS);
    }
}
