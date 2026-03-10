package com.vision4jpc.system.initializer.sysmodule;

import com.vision4jpc.common.utils.cache.SysConfigCacheUtil;
import com.vision4jpc.system.service.ISysModuleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.initializer.sysmodule
 * @className: SysModuleInitalizer
 * @author: WQL-KXJ
 * @description: TODO 初始化模块类(将模块的t信息提取Key-IsEnabled保存到redis中)
 * @date: 2024/9/28 18:34
 * @version: v1.0
 */
@Component
public class SysModuleInitalizer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    ISysModuleServcie sysModuleServcie;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //存储k-IsEnabled模块信息到redis中
        SysConfigCacheUtil.setShootModuleInfo(extractKeyAndAlter());
    }

    //把模块树状结构只提取Key和IsEnabled组成List<Map<String, String>>结构保存
    private List<Map<String, String>> extractKeyAndAlter() {
        return sysModuleServcie.selectModuleMapList();
    }
}
