package com.vision4jpc.common.utils.i18n;

import com.vision4jpc.common.utils.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils.i18n
 * @className: I18nUtils
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/21 10:06
 * @version: v1.0
 */
public class I18nUtils {

    private static final MessageSource messageSource;

    // 静态代码块初始化 MessageSource
    static {
        messageSource = SpringUtils.getBean(MessageSource.class);
    }

    /**
     * 获取对应键的国际化消息
     *
     * @param key 国际化键
     * @return 对应的国际化消息
     */
    public static String getValueByKey(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * 获取对应键的国际化消息，带有参数替换
     *
     * @param key  国际化键
     * @param args 替换参数
     * @return 对应的国际化消息
     */
    public static String getValueByKeyWithArgs(String key, Object[] args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    /**
     * 获取对应键的国际化消息，带有默认值
     *
     * @param key         国际化键
     * @param defaultMsg  默认消息
     * @return 对应的国际化消息或默认消息
     */
    public static String getValueByKeyOrDefault(String key, String defaultMsg) {
        return messageSource.getMessage(key, null, defaultMsg, LocaleContextHolder.getLocale());
    }

    /**
     * 获取指定 Locale 下的国际化消息
     *
     * @param key    国际化键
     * @param locale 指定的 Locale
     * @return 对应的国际化消息
     */
    public static String getValueByKeyWithLocale(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * 获取国际化消息，带有参数替换、默认值及异常处理
     *
     * @param key         国际化键
     * @param args        替换参数
     * @param defaultMsg  默认消息
     * @param locale      指定的 Locale
     * @return 对应的国际化消息或默认消息
     */
    public static String getValueByKeyWithFallback(String key, Object[] args, String defaultMsg, Locale locale) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            return defaultMsg;
        }
    }

}
