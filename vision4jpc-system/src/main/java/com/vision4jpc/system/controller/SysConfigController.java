package com.vision4jpc.system.controller;
import com.vision4jpc.common.core.controller.BaseController;
import com.vision4jpc.common.entity.results.http.HttpResultPojo;
import com.vision4jpc.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.controller
 * @className: SysConfigController
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/4 14:37
 * @version: v1.0
 */
@RestController
public class SysConfigController extends BaseController {

    @Autowired
    ISysConfigService sysConfigService;

    @GetMapping("/sysConfigVoList")
    public HttpResultPojo<Map<String, String>> selectConfigVoListVoListByConfigMenuId(Long configMenuID){
        return success(sysConfigService.selectSysConfigVoListByConfigMenuId(configMenuID));
    }

    @GetMapping("/sysConfigAllisEnabledVoList")
    public HttpResultPojo<Map<String, String>> selectAllMouldIsEnabledSysConfigList(){
        return success(sysConfigService.selectAllMouldIsEnabledSysConfigList());
    }

    @PutMapping("/sysConfigVoList")
    public HttpResultPojo<Object> updateSysConfig(@RequestBody Map<String,String> keyValue){
        sysConfigService.updateSysConfig(keyValue);
        return success();
    }

}