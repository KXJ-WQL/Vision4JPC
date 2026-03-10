package com.vision4jpc.common.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils.spring
 * @className: SpringUtil
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/19 12:28
 * @version: v1.0
 */
@Component
public class SpringUtils implements BeanFactoryPostProcessor {

    // Spring应用上下文环境的BeanFactory，用于管理Bean实例
    private static ConfigurableListableBeanFactory beanFactory;

    // BeanFactoryPostProcessor接口的方法，用于在BeanFactory标准初始化之后修改其内部Bean定义
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    // 根据Bean的名称获取Bean实例
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    // 根据Bean的Class类获取Bean实例
    public static <T> T getBean(Class<T> clz) throws BeansException {
        return beanFactory.getBean(clz);
    }

    // 判断BeanFactory是否包含一个与所给名称匹配的Bean定义
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }


    // 通过类型name获取Bean的类型
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }
}
