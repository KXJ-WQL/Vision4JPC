package com.vision4jpc.system.comm;
import com.vision4jpc.common.core.channel.NettyChannelManager;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetSocketAddress;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.comm
 * @className: HandlerHearBeat
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/20 14:12
 * @version: v1.0
 */
public class HandlerHearBeat extends ChannelDuplexHandler {

    private static final Logger log = LoggerFactory.getLogger(HandlerHearBeat.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断事件是否是空闲状态事件（IdleStateEvent）
        if(evt instanceof IdleStateEvent){
            IdleStateEvent e = (IdleStateEvent) evt;
            // 检查是否是读空闲（READER_IDLE）事件，即客户端一段时间没有发送数据
            if(e.state() == IdleState.READER_IDLE){
                // 获取 IP 地址
                String clientIp = getClientHostIP(ctx);
                // 打印超时信息
                log.error("心跳超时，关闭连接: " + clientIp);
                // 关闭连接
                ctx.disconnect();
                ctx.close();
                // 从ChannelManager中移除通道
                NettyChannelManager.removeChannel(clientIp);
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 消息为心跳包类型，响应一个Pong包
        if (msg instanceof PingWebSocketFrame){
            // 获取客户端的 IP 地址
            String clientIp = getClientHostIP(ctx);
            // 判断该客户端连接是否存在，存在即发送pong包进行响应，不存在不进行响应(避免假连接的情况)
            if(NettyChannelManager.isChannelExists(clientIp)) ctx.channel().writeAndFlush(new PongWebSocketFrame());
            else log.error("客户端: "+clientIp+"不存在不响应Pong包");
        }else {
            // 不是心跳帧，交给下一个处理器处理
            super.channelRead(ctx, msg);
        }
    }

    // 当通道发生异常时调用，通常是关闭连接以防止资源泄露。
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace(); // 打印异常堆栈信息
        ctx.disconnect();
        ctx.close(); // 关闭通道，防止进一步的资源占用
    }

    // 获取客户端纯IP地址
    private String getClientHostIP(ChannelHandlerContext ctx){
        // 获取客户端的 IP 地址，remoteAddress() 返回的是 SocketAddress(带端口号)，需要转换为 InetSocketAddress(纯IP格式)
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        // 获取 IP 地址并返回
        return socketAddress.getAddress().getHostAddress();
    }
}