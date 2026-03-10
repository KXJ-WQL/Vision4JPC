package com.vision4jpc.system.controller;
import com.vision4jpc.common.core.controller.BaseController;
import com.vision4jpc.common.entity.dao.SysNavigation;
import com.vision4jpc.common.entity.results.http.HttpResultPojo;
import com.vision4jpc.system.entity.vo.NavigationRouterVo;
import com.vision4jpc.system.service.ISysNavigationService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.controller
 * @className: SysNavigationController
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/28 18:11
 * @version: v1.0
 */
@RestController
public class SysNavigationController extends BaseController {

    @Autowired
    ISysNavigationService sysNavigationService;

    @GetMapping("/sysNavigationTreeVoList")
    public HttpResultPojo<List<NavigationRouterVo>> selectNavigationTreeVoList(){
        return success(sysNavigationService.selectNavigationTreeVoList());
    }

    @GetMapping("/sysNavigationTreeList")
    public HttpResultPojo<List<SysNavigation>> selectNavigationTreeList(){
        return success(sysNavigationService.selectNavigationTreeList());
    }

    @PostMapping("/sysNavigation")
    public HttpResultPojo<Object> addNavigation(@RequestBody SysNavigation sysNavigation){
        return success(sysNavigationService.addNavigation(sysNavigation));
    }

    @PutMapping("/sysNavigation")
    public HttpResultPojo<Object> updateNavigation(@RequestBody SysNavigation sysNavigation){
        return success(sysNavigationService.updateNavigation(sysNavigation));
    }

    @DeleteMapping("/sysNavigation")
    public HttpResultPojo<Object> deleteNavigation(@RequestBody List<Integer> navigationIdList){
        sysNavigationService.deleteNavigationById(navigationIdList);
        return success();
    }
}
