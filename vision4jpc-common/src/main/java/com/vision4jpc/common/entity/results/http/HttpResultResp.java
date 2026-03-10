package com.vision4jpc.common.entity.results.http;

import com.vision4jpc.common.constant.HttpStatus;
import com.vision4jpc.common.utils.i18n.I18nUtils;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.domain.results.http
 * @className: HttpResultResp
 * @author: WQL-KXJ
 * @description: TODO HTTP响应数据封装方法类
 * @date: 2024/8/20 20:43
 * @version: v1.0
 */
public class HttpResultResp {

    public static <T> HttpResultPojo<T> success(Integer code,String message,T data){return new HttpResultPojo(code,message,data);};

    public static <T> HttpResultPojo<T> success(Integer code,String message){return new HttpResultPojo(code,message,null);};

    public static <T> HttpResultPojo<T> success(T data){return new HttpResultPojo(HttpStatus.SUCCESS, I18nUtils.getValueByKey("sys1"),data);};

    public static <T> HttpResultPojo<T> success(){return new HttpResultPojo(HttpStatus.SUCCESS, I18nUtils.getValueByKey("sys1"),null);};

    public static <T> HttpResultPojo<T> error(Integer code,String message,T data){return new HttpResultPojo(code,message,data);};

    public static <T> HttpResultPojo<T> error(Integer code,String message){return new HttpResultPojo(code,message,null);};

    public static <T> HttpResultPojo<T> error(String message){return new HttpResultPojo(HttpStatus.ERROR,message,null);};

    public static <T> HttpResultPojo<T> error(){return new HttpResultPojo(HttpStatus.ERROR,I18nUtils.getValueByKey("sys2"),null);};

    public static <T> HttpResultPojo<T> wran(Integer code,String message,T data){return new HttpResultPojo(code,message,data);};

    public static <T> HttpResultPojo<T> wran(Integer code,String message){return new HttpResultPojo(code,message,null);};

    public static <T> HttpResultPojo<T> wran(String message){return new HttpResultPojo(HttpStatus.WARN,message,null);};
}
