package com.vision4jpc.system.controller;

import com.vision4jpc.common.core.controller.BaseController;
import com.vision4jpc.common.entity.dao.SysModule;
import com.vision4jpc.common.entity.results.http.HttpResultPojo;
import com.vision4jpc.system.service.ISysModuleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.controller
 * @className: SysModuleController
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/27 11:40
 * @version: v1.0
 */
@RestController
public class SysModuleController extends BaseController {

    @Autowired
    ISysModuleServcie sysModuleServcie;

    @GetMapping("/sysModuleTreeList")
    public HttpResultPojo<List<SysModule>> selectModuleTreeList(){
        //获取树形功能模块列表并返回
        return success(sysModuleServcie.selectModuleTreeList());
    }

    @GetMapping("/sysModuleList")
    public HttpResultPojo<List<SysModule>> selectModuleList(){
        //获取普通功能模块列表并返回
        return success(sysModuleServcie.selectModuleList());
    }

    @GetMapping("/sysModuleMap")
    public HttpResultPojo<List<Map<String,String>>> selectModuleTreeMap(){
        //获取功能模块Map信息并返回
        return success(sysModuleServcie.selectModuleMap());
    }

    @PutMapping ("/sysModuleTreeList")
    public HttpResultPojo<Integer> updateModule(@RequestBody SysModule sysModule){
        return toAjaxRows(sysModuleServcie.updateModule(sysModule));
    }

    @PostMapping("/sysModuleTreeList")
    public HttpResultPojo<Integer> addModule(@RequestBody SysModule sysModule){
        return success(sysModuleServcie.addModule(sysModule));
    }

    @DeleteMapping("/sysModuleTreeList")
    public HttpResultPojo<Object> deleteModule(@RequestBody List<Integer> moduleIdList){
        sysModuleServcie.deleteModule(moduleIdList);
        return success();
    }
}
