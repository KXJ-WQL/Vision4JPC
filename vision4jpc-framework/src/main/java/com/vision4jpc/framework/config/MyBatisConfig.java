package com.vision4jpc.framework.config;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.framework.config
 * @className: MyBatisConfig
 * @author: WQL-KXJ
 * @description: TODO 手动配置Mybatis的别名和Mapper包扫描
 * @date: 2024/8/27 10:39
 * @version: v1.0
 */

@Configuration
@MapperScan("com.vision4jpc.*.mapper")
public class MyBatisConfig {

    // 自动注入Spring环境变量
    @Autowired
    private Environment env;

    // 默认的资源模式，用于匹配类文件
    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    /**
     * 动态设置MyBatis的typeAliasesPackage属性，扫描指定包路径下的所有类，并将其别名注册到MyBatis中。
     *
     * @param typeAliasesPackage 要扫描的包路径，多个路径使用逗号分隔
     * @return 处理后的包路径字符串
     */
    public static String setTypeAliasesPackage(String typeAliasesPackage) {
        // 资源解析器，解析指定的资源路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // Metadata读取工厂，用于读取类的元数据
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        // 存储所有扫描到的包路径
        List<String> allResult = new ArrayList<>();

        try {
            // 处理每个typeAliasesPackage路径，多个路径用逗号分隔
            for (String aliasesPackage : typeAliasesPackage.split(",")) {
                List<String> result = new ArrayList<>();
                // 构建资源路径，扫描匹配的类
                aliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
                // 获取资源路径下的所有类文件
                Resource[] resources = resolver.getResources(aliasesPackage);
                if (resources != null && resources.length > 0) {
                    MetadataReader metadataReader = null;
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
                            try {
                                // 获取类的包名并加入结果集
                                result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (result.size() > 0) {
                    // 去重后将结果加入到最终的包路径集合中
                    HashSet<String> hashResult = new HashSet<>(result);
                    allResult.addAll(hashResult);
                }
            }
            // 如果扫描到的包路径不为空，则返回拼接后的字符串
            if (allResult.size() > 0) {
                typeAliasesPackage = String.join(",", allResult.toArray(new String[0]));
            } else {
                throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return typeAliasesPackage;
    }

    /**
     * 解析并加载Mapper XML配置文件，将其转为Resource数组。
     *
     * @param mapperLocations MyBatis Mapper文件的路径数组
     * @return 解析后的Resource数组
     */
    public Resource[] resolveMapperLocations(String[] mapperLocations) {
        // 资源解析器，解析指定的资源路径
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<>();
        if (mapperLocations != null) {
            for (String mapperLocation : mapperLocations) {
                try {
                    // 获取Mapper XML文件
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 将资源列表转为数组并返回
        return resources.toArray(new Resource[0]);
    }

    /**
     * 配置并创建SqlSessionFactory Bean，配置MyBatis相关属性。
     *
     * @param dataSource 数据源，MyBatis用于连接数据库
     * @return 配置好的SqlSessionFactory对象
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // 获取配置文件中的typeAliasesPackage、mapperLocations和configLocation属性
        String typeAliasesPackage = env.getProperty("mybatis.typeAliasesPackage");
        String mapperLocations = env.getProperty("mybatis.mapperLocations");
        String configLocation = env.getProperty("mybatis.configLocation");

        // 动态设置typeAliasesPackage
        typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);

        // 添加SpringBootVFS实现类
        VFS.addImplClass(SpringBootVFS.class);

        // 配置SqlSessionFactoryBean
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);  // 设置数据源
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);  // 设置类型别名包
        sessionFactory.setMapperLocations(resolveMapperLocations(new String[]{mapperLocations}));  // 设置Mapper文件位置
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));  // 设置MyBatis配置文件位置

        // 返回配置好的SqlSessionFactory对象
        return sessionFactory.getObject();
    }
}
