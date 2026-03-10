package com.vision4jpc.system.comm;

import com.vision4jpc.system.handler.ConnectionHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AttributeKey;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.comm
 * @className: WebSocketFrameHandler
 * @author: WQL-KXJ
 * @description: TODO 负责处理 WebSocket 连接中的 I/O 操作和事件
 * @date: 2024/8/20 11:51
 * @version: v1.0
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    // 创建一个 AttributeKey 用于将 ConnectionHandler 绑定到每个连接的上下文 (ChannelHandlerContext) 上
    private static final AttributeKey<ConnectionHandler> CONNECTION_HANDLER_KEY = AttributeKey.valueOf("connectionHandler");

    // 当新的连接加入时触发, 此时为每个新的连接创建一个 ConnectionHandler，并将其绑定到该连接的上下文中
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 创建新的 ConnectionHandler 实例，传递当前连接上下文 ctx
        ConnectionHandler connectionHandler = new ConnectionHandler(ctx);
        // 将 ConnectionHandler 绑定到当前连接的 ChannelHandlerContext 上，存储在其 Attribute 中
        ctx.channel().attr(CONNECTION_HANDLER_KEY).set(connectionHandler);
        // 调用 ConnectionHandler 的初次连接逻辑
        connectionHandler.handleInitialConnection();
    }

    // 当通道（连接）变为活跃状态时调用, 常是在连接建立并准备好通信时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 从上下文中获取与该连接绑定的 ConnectionHandler
        ConnectionHandler connectionHandler = ctx.channel().attr(CONNECTION_HANDLER_KEY).get();
        // 调用通道激活处理逻辑
        if (connectionHandler != null) connectionHandler.handleChannelActive();
    }

    // 当通道（连接）变为非活跃状态时调用, 这意味着连接已经断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 从上下文中获取与该连接绑定的 ConnectionHandler
        ConnectionHandler connectionHandler = ctx.channel().attr(CONNECTION_HANDLER_KEY).get();
        // 调用通道断开处理逻辑
        if (connectionHandler != null) connectionHandler.handleChannelInactive();
    }

    // 处理 WebSocketFrame 类型的消息, 当收到 WebSocket 客户端发送的消息时调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof TextWebSocketFrame) {
            // 从上下文中获取与该连接绑定的 ConnectionHandler
            ConnectionHandler connectionHandler = ctx.channel().attr(CONNECTION_HANDLER_KEY).get();
            // 调用客户端消息处理方法
            connectionHandler.handleChannelMessage(frame);
        }
    }

    // 处理异常,当通道中出现异常时触发, 通常用于关闭连接和释放资源
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace(); // 打印异常堆栈
        ctx.disconnect(); // 断开连接并关闭通道
        ctx.close();
    }

    // 处理用户自定义事件在此例中, 处理 WebSocket 握手完成事件
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        // 如果事件是 WebSocket 握手完成
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            // 检查请求的 URI 路径是否匹配
            String requestUri = handshakeComplete.requestUri();
            if (!requestUri.equals("/targetlocation")) {
                // 如果 URI 不符合要求，断开连接
                ctx.disconnect();
                ctx.close();
            }
        }
    }
}

