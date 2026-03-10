package com.vision4jpc.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.properties
 * @className: NettyProperties
 * @author: WQL-KXJ
 * @description: TODO Netty配置文件信息对象
 * @date: 2024/8/20 10:56
 * @version: v1.0
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    //Websocket服务端口
    private Integer serverPort;

    //URI路径
    private String websocketPath;

    //BossEventLoopGroup线程数
    private Integer bossThread;

    //WorkerEventLoopGroup线程数
    private Integer workerThread;

    //当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
    private Integer backlog;

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getWebsocketPath() {
        return websocketPath;
    }

    public void setWebsocketPath(String websocketPath) {
        this.websocketPath = websocketPath;
    }

    public Integer getBossThread() {
        return bossThread;
    }

    public void setBossThread(Integer bossThread) {
        this.bossThread = bossThread;
    }

    public Integer getWorkerThread() {
        return workerThread;
    }

    public void setWorkerThread(Integer workerThread) {
        this.workerThread = workerThread;
    }

    public Integer getBacklog() {
        return backlog;
    }

    public void setBacklog(Integer backlog) {
        this.backlog = backlog;
    }
}
