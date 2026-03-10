package com.vision4jpc.system.converter;

import com.vision4jpc.system.entity.dao.SysConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.converter
 * @className: SysConfigConvert
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/4 16:04
 * @version: v1.0
 */

@Mapper(componentModel = "spring")
public interface SysConfigConvert {

    // 获取 SysConfigConvert 实例
    SysConfigConvert INSTANCE = Mappers.getMapper(SysConfigConvert.class);

    //将 SysConfig 列表转换为 List<Map<String, String>>(每个 Map 代表一个 SysConfig 对象的键值对)
    default Map<String, String> sysConfigsToMaps(List<SysConfig> sysConfigList) {
        // 将每个 SysConfig 对象转换为 Map，并收集到 Map 中
        return sysConfigList.stream()
                .collect(Collectors.toMap(
                        SysConfig::getKey, // Map 的键是 SysConfig 的 key
                        SysConfig::getValue // Map 的值是一个包含 key 和 value 的 Map
                ));}

    // Map 的键是 SysConfig 的 key，值是 SysConfig 的 value。
    private Map<String, String> convertToMap(SysConfig sysConfig) {
        // 创建一个新的 HashMap
        Map<String, String> map = new HashMap<>();
        // 将 SysConfig 对象的 key 和 value 放入 Map
        map.put(sysConfig.getKey(), sysConfig.getValue());
        return map;
    }
}
