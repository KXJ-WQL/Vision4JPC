package com.vision4jpc.common.utils.resttemplate;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;

import com.vision4jpc.common.constant.HttpStatus;
import com.vision4jpc.common.exceptions.system.SysServiceException;
import com.vision4jpc.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils.resttemplate
 * @className: RestTemplateUtil
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/23 0:35
 * @version: v1.0
 */
public class RestTemplateUtil {
    private static RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(RestTemplateUtil.class);

    // 初始化方法，使用 SpringUtils 获取 RestTemplate 实例
     static{
        restTemplate = SpringUtils.getBean(RestTemplate.class);
    }

    // 发送 GET 请求并指定解析类型
    public static <T> T getForEntity(String url, Map<String, String> params, Class<T> responseType) {
        try {
            // 使用 UriComponentsBuilder 构建带参数的 URL
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

            // 将查询参数附加到 URL 上
            if (params != null && !params.isEmpty()) params.forEach(builder::queryParam);

            // 发起 GET 请求
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(builder.toUriString(), responseType);

            log.error("边缘主机服务完成响应信息: {}", responseEntity.getBody().toString());

            // 返回响应体
            return responseEntity.getBody();
        }catch (RestClientException e){
            // 捕获 RestTemplate 的所有异常并抛出自定义异常
            throw new SysServiceException("edgehostmanager1", HttpStatus.ERROR);
        }

    }

    // 模拟报靶发送 POST 请求并指定解析类型
    public static <T> T postForSimulateObject(String url, Object request, Class<T> responseType) {
        try {
            // 使用 RestTemplate 发送 POST 请求，并获取返回的 JSON 字符串
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            log.error("激光模拟图像识别处理完成响应信息: {}", response.getBody().toString());

            // 将 JSON 字符串解析为指定的Class泛型类型 T
            return JSON.parseObject(response.getBody(), responseType);
        }catch (RestClientException e){
            // 捕获 RestTemplate 的所有异常并抛出自定义异常
            throw new SysServiceException("shoot19", HttpStatus.ERROR);
        }
    }

    // 实弹报靶发送 POST 请求并动态指定解析类型
    public static <T> T postForActualObject(String url, Object request, TypeReference<T> responseType) {
        try {
            // 使用 RestTemplate 发送 POST 请求，并获取返回的 JSON 字符串
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // 打印日志用的 JSON
            log.error("{}号靶位图像识别处理完成响应信息: 子弹数{} 响应:{}", MDC.get("targetPosition"), MDC.get("bullerNumber"), simplifyLogoutput(response.getBody()));

            // 将 JSON 字符串解析为指定的泛型类型 T
            return JSON.parseObject(response.getBody(), responseType);
        } catch (RestClientException e) {
            e.printStackTrace();
            // 捕获 RestTemplate 的所有异常并抛出自定义异常
            throw new SysServiceException("shoot19", HttpStatus.ERROR);
        }
    }

    // 指定请求方式通用http调用方法
    public static <T> T exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) {
        ResponseEntity<T> response = restTemplate.exchange(url, method, requestEntity, responseType);
        return response.getBody();
    }

    // 简化日志输出
    private static String simplifyLogoutput(String responseBody){
        // 使用 Fastjson 解析为 JSONObject
        JSONObject root = JSON.parseObject(responseBody);
        JSONObject data = root.getJSONObject("data");
        // 热成像的base64图像省略，简化日志打印
        if (data != null && data.containsKey("img_data_vis_base64")) {
            // 替换 base64 字段值为 "[base64省略]"
            data.put("img_data_vis_base64", "[base64省略]");
        }
        return root.toJSONString();
    }
}
