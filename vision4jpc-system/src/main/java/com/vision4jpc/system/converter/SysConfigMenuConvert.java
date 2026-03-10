package com.vision4jpc.system.converter;

import com.vision4jpc.system.entity.dao.SysConfigMenu;
import com.vision4jpc.system.entity.vo.SysConfigMenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.converter
 * @className: SysConfigMenuConvert
 * @author: WQL-KXJ
 * @description: TODO SysConfigMenu转变为ConfigMenuVo的转化方法
 * @date: 2024/9/1 14:55
 * @version: v1.0
 */
@Mapper(componentModel = "spring")
public interface SysConfigMenuConvert {
    SysConfigMenuConvert INSTANCE = Mappers.getMapper(SysConfigMenuConvert.class);

    //单对象映射
    SysConfigMenuVo sysConfigMenuToConfigMenuVo(SysConfigMenu sysConfigMenu);

    //列表对象映射
    List<SysConfigMenuVo> sysConfigMenuToConfigMenuVos(List<SysConfigMenu> sysConfigMenuList);
}
