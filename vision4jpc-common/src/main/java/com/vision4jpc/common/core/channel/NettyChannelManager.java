package com.vision4jpc.common.core.channel;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.shootinglivecore.channel
 * @className: ChannelManager
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/8 16:50
 * @version: v1.0
 */
public class NettyChannelManager {

    private static final Logger log = LoggerFactory.getLogger(NettyChannelManager.class);

    // 使用线程安全的 ConcurrentHashMap 来保存客户端的 Channel
    private static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    // 添加客户端连接
    public static void addChannel(String ip, Channel channel) {
        channelMap.put(ip, channel);
    }

    // 移除客户端连接
    public static void removeChannel(String ip) {
        channelMap.remove(ip);
    }

    // 判断客户端连接是否存在
    public static boolean isChannelExists(String ip) {
        return channelMap.containsKey(ip);
    }

    // 发送消息到指定IP的客户端携带回调的消费者函数
    public static void sendMessageCallback(String ip, String message, Consumer<Boolean> callback) {
        Channel channel = channelMap.get(ip);
        //通道是否存在且存活
        if (channel != null && channel.isActive()) {
            //异步发送数据并添加回调
            channel.writeAndFlush(new TextWebSocketFrame(message)).addListener(future -> {
                //消息是否发送成功
                if (future.isSuccess()) {
                    log.info("消息发送成功: " );
                    callback.accept(true);
                }else{
                    log.info("消息发送失败: " );
                    callback.accept(false);
                }
            });
        } else {
            log.error(ip+"连接已关闭!不存在");
            callback.accept(false);
        }
    }

    // 发送消息到指定IP的客户端
    public static void sendMessage(String ip, String message) {
        Channel channel = channelMap.get(ip);
        //通道是否存在且存活
        if (channel != null && channel.isActive()) {
            //异步发送数据并添加回调
            channel.writeAndFlush(new TextWebSocketFrame(message)).addListener(future -> {
                //消息是否发送成功
                if (future.isSuccess()) {
                    log.info("消息发送成功: " );
                }
            });
        } else {
            log.error(ip+"连接已关闭!不存在");
        }
    }

    // 发送消息给全部客户端
    public static void sendAllMessage(String message) {
        for (Channel channel : channelMap.values()) {
            if (channel.isActive()) {
                //异步发送数据并添加回调
                channel.writeAndFlush(new TextWebSocketFrame(message)).addListener(future -> {
                    if (!future.isSuccess()) {
                        log.error("消息发送失败: " + future.cause().getMessage());
                    }});
            }
        }
    }

    // 发送异常消息给控制后台
    public static void sendExceptionMessage(String message) {
        Channel channel = channelMap.get("127.0.0.1");
        //通道是否存在且存活
        if (channel != null && channel.isActive()) {
            //异步发送数据并添加回调
            channel.writeAndFlush(new TextWebSocketFrame(message));
        } else {
            log.error("控制后台前端系统未打开");
        }
    }

}
