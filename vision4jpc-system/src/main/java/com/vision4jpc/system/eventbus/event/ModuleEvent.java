package com.vision4jpc.system.eventbus.event;

import java.util.List;
import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.eventbus.event
 * @className: ModuleEvent
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/28 20:08
 * @version: v1.0
 */
public class ModuleEvent {

    //模块信息
   private List<Map<String, String>> moduleInfo;

    public ModuleEvent(List<Map<String, String>> moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    public List<Map<String, String>> getModuleInfo() {
        return moduleInfo;
    }

    public void setModuleInfo(List<Map<String, String>> moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    @Override
    public String toString() {
        return "ModuleEvent{" +
                "moduleInfo=" + moduleInfo +
                '}';
    }
}
