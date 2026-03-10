package com.vision4jpc.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.vision4jpc") //扫描com.vision4jpc(解决多模块bean注入问题)
@Slf4j
public class WhShootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhShootApplication.class, args);
        // Springboot完全启动后输出，前端程序的spawn命令以这个输出证明程序完全启动然后进入控制台页面
        log.info("Vision4JPC started successfully");
    }
}
