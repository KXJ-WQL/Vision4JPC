package com.vision4jpc.framework.config;


import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.config
 * @className: RestTemplateConfig
 * @author: WQL-KXJ
 * @description: RestTemplate http远程调用配置
 * @date: 2024/9/22 12:04
 * @version: v1.0
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 配置连接池管理器
     * PoolingHttpClientConnectionManager 使用了连接池的概念，可以重用连接，避免频繁创建销毁连接。
     * 这个Bean用于管理HTTP连接的生命周期和配置最大并发连接数，减少网络资源的消耗。
     */
    @Bean
    public HttpClientConnectionManager poolingHttpClientConnectionManager() {
        // 创建连接池管理器
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 设置最大连接数为500（整个连接池）
        poolingHttpClientConnectionManager.setMaxTotal(500);
        // 设置每个路由（或主机）的最大并发数为100
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
        return poolingHttpClientConnectionManager;
    }

    /**
     * 配置 HttpClient 实例
     * HttpClient 是 Apache 提供的强大的 HTTP 客户端库，它支持多种高级功能如连接池、线程管理、重试等。
     * 我们在这里将连接池与 HttpClient 绑定，以便 HttpClient 能够使用我们配置的连接池管理器。
     */
    @Bean
    public CloseableHttpClient httpClient(HttpClientConnectionManager poolingHttpClientConnectionManager) {
        // 使用 HttpClientBuilder 自定义创建 HttpClient 实例
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置连接池管理器到 HttpClient 中
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        // 配置空闲连接回收策略：回收超过 30 秒未使用的连接
        httpClientBuilder.evictIdleConnections(Timeout.ofSeconds(30));
        // 返回配置好的 HttpClient 实例
        return httpClientBuilder.build();
    }

    /**
     * 配置 ClientHttpRequestFactory（请求工厂）
     * ClientHttpRequestFactory 是 RestTemplate 底层使用的工厂类，用于创建 HTTP 请求。
     * 使用 HttpComponentsClientHttpRequestFactory 来指定 HttpClient 作为底层的 HTTP 客户端。
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(CloseableHttpClient httpClient) {
        // 使用 HttpComponentsClientHttpRequestFactory 来构建请求工厂
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        // 设置自定义的 HttpClient
        clientHttpRequestFactory.setHttpClient(httpClient);
        // 设置连接超时时间（单位：毫秒），即建立 TCP 连接的时间
        clientHttpRequestFactory.setConnectTimeout(5000);
        // 设置从连接池获取连接的超时时间（单位：毫秒）
        clientHttpRequestFactory.setConnectionRequestTimeout(8000);
        // 设置读取数据的超时时间（单位：毫秒）
        clientHttpRequestFactory.setReadTimeout(5000);
        return clientHttpRequestFactory;
    }

    /**
     * 配置 RestTemplate
     * RestTemplate 是 Spring 提供的用于发送 HTTP 请求的客户端工具默认使用JDK的HttpURLConnection(性能不是很好)
     * 通过自定义的 ClientHttpRequestFactory 为 RestTemplate 配置 HttpClient 作为底层实现
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();
        // 设置自定义的请求工厂
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        return restTemplate;
    }
}
