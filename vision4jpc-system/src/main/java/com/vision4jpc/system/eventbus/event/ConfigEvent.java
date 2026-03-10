package com.vision4jpc.system.eventbus.event;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.eventbus.event
 * @className: ConfigEvent
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/28 20:09
 * @version: v1.0
 */
public class ConfigEvent {

    //配置信息
   private Map<String, String> configInfo;

    public ConfigEvent(Map<String, String> configInfo) {
        this.configInfo = configInfo;
    }

    public Map<String, String> getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(Map<String, String> configInfo) {
        this.configInfo = configInfo;
    }

    @Override
    public String toString() {
        return "ConfigEvent{" +
                "configInfo=" + configInfo +
                '}';
    }
}
