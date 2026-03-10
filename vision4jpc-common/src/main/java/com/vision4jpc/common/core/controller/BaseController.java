package com.vision4jpc.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vision4jpc.common.entity.results.http.HttpResultPojo;
import com.vision4jpc.common.entity.results.http.HttpResultResp;
import com.vision4jpc.common.utils.i18n.I18nUtils;

import java.util.List;
import java.util.function.Supplier;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.shootinglivecore.controller
 * @className: BaseController
 * @author: WQL-KXJ
 * @description: TODO Controller的基础辅助类
 * @date: 2024/9/7 0:18
 * @version: v1.0
 */
public class BaseController {

    //返回成功
    public HttpResultPojo success() {
        return HttpResultResp.success();
    }

    //返回失败消息
    public HttpResultPojo error() {
        return HttpResultResp.error();
    }

    //返回带消息成功消息
    public HttpResultPojo success(String message) {
        //文字国际化处理
        return HttpResultResp.success(I18nUtils.getValueByKey(message));
    }

    //返回带消息/数据/状态码成功消息
    public HttpResultPojo success(Integer code,String message,Object data) {
        return HttpResultResp.success(code, message, data);
    }

    public <T> PageInfo<T> paginate(int page, int size, Supplier<List<T>> supplier) {
        // 设置分页参数
        PageHelper.startPage(page, size);
        // 执行查询操作(延迟内部执行查询)
        List<T> list = supplier.get();
        // 返回分页信息
        return new PageInfo<>(list);
    }

    //返回带数据成功消息
    public HttpResultPojo success(Object data) {
        return HttpResultResp.success(data);
    }

    //返回失败消息
    public HttpResultPojo error(String message) {
        return HttpResultResp.error(I18nUtils.getValueByKey(message));
    }

    //返回警告消息
    public HttpResultPojo warn(String message) {
        return HttpResultResp.wran(I18nUtils.getValueByKey(message));
    }

    //添加和更新操作影响行数判断 - 响应返回结果
    protected HttpResultPojo toAjaxRows(int rows) {
        return rows > 0 ? success() : error();
    }

    //操作Boolean判断 - 响应返回结果
    protected HttpResultPojo toAjax(boolean result) {
        return result ? success() : error();
    }
}
