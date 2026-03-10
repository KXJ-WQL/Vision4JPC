package com.vision4jpc.system.initializer.syswebsocket;

import com.vision4jpc.system.comm.WebSocketChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.comm
 * @className: NettyWebSocketServer
 * @author: WQL-KXJ
 * @description: TODO 报靶websocket通信
 * @date: 2024/8/20 11:33
 * @version: v1.0
 */
@Component
public class NettyWebSocketServerInitializer {

    // 用于接收客户端连接的主线程组
    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    // 用于处理每一个连接的数据读写的工作线程组
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    private ChannelFuture channelFuture;

    @PostConstruct // bean实例化后立即执行方法
    @DependsOn("redisTemplate") // 在netty启动之前保证redisTemplate已加载完整，因为初次连接会使用redis补发
    public void startServer() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup);
            // 指定通道类型为 NioServerSocketChannel，用于非阻塞的I/O操作。
            serverBootstrap.channel(NioServerSocketChannel.class)
                    // 设置通道的处理器, 在连接建立后，将使用 WebSocketServerInitializer 处理具体的 I/O 事件。
                    .childHandler(new WebSocketChannelInitializer())
                    // 设置服务器套接字的选项，如设置TCP的最大连接数为128。这个选项表示队列中允许等待连接的最大数量。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 为每个已接受的连接设置选项，使得在连接空闲时TCP保持连接活跃（即启用TCP心跳机制）。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，启动服务器
            channelFuture = serverBootstrap.bind(9999).sync();
            System.out.println("netty-9999启动!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void stop() {
        if (channelFuture != null) {
            // 强制关闭服务器通道
            channelFuture.channel().close();  // 立即关闭通道);
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        System.out.println("Netty 关闭服务器通道!");
    }
}