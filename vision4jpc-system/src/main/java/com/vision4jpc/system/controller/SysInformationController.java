package com.vision4jpc.system.controller;

import com.vision4jpc.common.core.controller.BaseController;
import com.vision4jpc.common.entity.results.http.HttpResultPojo;
import com.vision4jpc.system.entity.vo.SysInformationVo;
import com.vision4jpc.system.service.ISysInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.controller
 * @className: SysInformationController
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/12/11 17:57
 * @version: v1.0
 */
@RestController
public class SysInformationController extends BaseController {

    @Autowired
    ISysInformationService sysInformationService;

    @GetMapping("/sysInformation")
    public HttpResultPojo<List<SysInformationVo>> selectInformationVoList(){
        return success(sysInformationService.getAllSysInformation());
    }

    @GetMapping("/sysInformationVoListByID")
    public HttpResultPojo<SysInformationVo> selectInformationVoListByID(Integer informationId){
        return success(sysInformationService.getSysInformationByID(informationId));
    }

    @PutMapping("/sysInformation")
    public HttpResultPojo<Integer> updateAllToRead(){
        return success(sysInformationService.updateAllToRead());
    }

    @DeleteMapping("/sysInformation")
    public HttpResultPojo<Integer> deleteSysInformation(@RequestBody List<Long> informationIdList){
        return success(sysInformationService.deleteSysInformation(informationIdList));
    }
}
