package com.vision4jpc.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.config
 * @className: WebConfig
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/8 17:25
 * @version: v1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 配置静态资源的映射路径和资源位置
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置项目中的静态资源路径为根路径（"/"），并从多个资源位置加载静态资源
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/resources/");

        // 配置与页面相关的静态资源路径为“/#/”，从static文件夹加载资源
        registry.addResourceHandler("/#/**").addResourceLocations("classpath:/static/");

    }

    //解决跨域问题，允许特定请求跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径的跨域请求
                .allowedHeaders("*")  // 允许的请求头
                .allowedMethods("*")  // 允许的HTTP请求方法，"*"代表允许所有方法
                .allowedOrigins("*")  // 允许所有来源的请求
                .maxAge(3600);// 跨域允许时间
    }
}
