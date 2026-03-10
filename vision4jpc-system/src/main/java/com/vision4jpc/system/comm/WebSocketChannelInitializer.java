package com.vision4jpc.system.comm;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.comm
 * @className: WebSocketServerInitializer
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/20 11:49
 * @version: v1.0
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    /** 定义一个心跳处理的线程池，使用 16 个线程。心跳任务通常负载较轻，16 个线程可以满足大多数心跳检测的需求
        如果心跳频率特别高或者连接数特别多，可以考虑增加线程数。**/
    private static final EventExecutorGroup HEARTBEAT_GROUP = new DefaultEventExecutorGroup(16);

    /** 定义一个业务处理的线程池，使用 20 个线程。业务处理通常负载较大，涉及复杂的逻辑、数据处理等
        20个线程可以适应中等规模的并发量。如果业务逻辑复杂或并发量大，可以适当增加线程数**/
    private static final EventExecutorGroup BUSINESS_GROUP = new DefaultEventExecutorGroup(20);

    @Override
    protected void initChannel(SocketChannel ch) {
        // 获取管道
        ChannelPipeline pipeline = ch.pipeline();

        // HTTP 编解码器，处理HTTP请求和响应
        pipeline.addLast(new HttpServerCodec());

        // 用于处理分块的数据流，支持大文件传输
        pipeline.addLast(new ChunkedWriteHandler());

        // 聚合HTTP消息，将多个消息转换为单一的FullHttpRequest或FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));

        // 心跳机制，检测客户端是否在线
        pipeline.addLast(HEARTBEAT_GROUP, new IdleStateHandler(12, 0, 0, TimeUnit.SECONDS));

        //心跳检测处理器
        pipeline.addLast(HEARTBEAT_GROUP, new HandlerHearBeat());

        // 自定义业务处理器，处理WebSocket的消息
        pipeline.addLast(BUSINESS_GROUP, new WebSocketFrameHandler());

        // 设置WebSocket协议路径，自动处理WebSocket握手、Ping、Pong、关闭帧
        pipeline.addLast(new WebSocketServerProtocolHandler("/", null, true, 65536 * 10,true,true));
    }
}
