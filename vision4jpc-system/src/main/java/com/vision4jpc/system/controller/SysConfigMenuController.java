package com.vision4jpc.system.controller;
import com.vision4jpc.common.core.controller.BaseController;
import com.vision4jpc.common.entity.results.http.HttpResultPojo;
import com.vision4jpc.system.entity.dao.SysConfigMenu;
import com.vision4jpc.system.entity.vo.SysConfigMenuVo;
import com.vision4jpc.system.service.ISysConfigMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.controller
 * @className: SysConfigMenuController
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/1 14:50
 * @version: v1.0
 */
@RestController
public class SysConfigMenuController extends BaseController {

    @Autowired
    ISysConfigMenuService sysConfigMenuService;

    @GetMapping("/sysConfigMenuVoList")
    public HttpResultPojo<List<SysConfigMenuVo>> selectConfigMenuVoList(){
        return success(sysConfigMenuService.selectConfigMenuVoList());
    }

    @GetMapping("/sysConfigMenu")
    public HttpResultPojo<List<SysConfigMenu>> selectConfigMenuList(){
        return success(sysConfigMenuService.selectConfigMenuList());
    }

    @PostMapping("/sysConfigMenu")
    public HttpResultPojo<Object> addConfigMenu(@RequestBody SysConfigMenu sysConfigMenu) {
        return success(sysConfigMenuService.addConfigMenu(sysConfigMenu));
    }

    @PutMapping("/sysConfigMenu")
    public HttpResultPojo<Object> updateConfigMenu(@RequestBody SysConfigMenu sysConfigMenu) {
        return success(sysConfigMenuService.updateConfigMenu(sysConfigMenu));
    }

    @DeleteMapping("/sysConfigMenu")
    public HttpResultPojo<Object> deleteConfigMenuById(@RequestBody List<Integer> sysConfigMenuIds) {
        sysConfigMenuService.deleteConfigMenuById(sysConfigMenuIds);
        return success();
    }
}
