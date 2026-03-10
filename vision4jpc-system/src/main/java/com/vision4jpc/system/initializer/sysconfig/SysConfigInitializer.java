package com.vision4jpc.system.initializer.sysconfig;

import com.vision4jpc.common.utils.cache.SysConfigCacheUtil;
import com.vision4jpc.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.initializer.sysmodule
 * @className: SysConfigInitializer
 * @author: WQL-KXJ
 * @description: TODO 初始化配置类(应用启动时存储配置信息到Redis中)
 * @date: 2024/9/28 18:34
 * @version: v1.0
 */
@Component
public class SysConfigInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    ISysConfigService sysConfigService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //存储系统配置信息到redis中
        loadConfigurations().forEach((key,value)-> SysConfigCacheUtil.setAllConfigurations(key,value));
    }

    private Map<String, String> loadConfigurations() {
        // 获取系统配置
        return sysConfigService.selectAllSysConfigList();
    }
}
