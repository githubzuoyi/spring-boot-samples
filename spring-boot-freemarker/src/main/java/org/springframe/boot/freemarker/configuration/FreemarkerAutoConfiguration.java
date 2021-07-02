package org.springframe.boot.freemarker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

/**
 * 自动装配 freemarker 相关 bean
 * <p>
 * bean 装配的层次性
 * 公共配置
 * web环境
 * 非web环境
 */
@org.springframework.context.annotation.Configuration
@ConditionalOnClass({FreeMarkerConfigurationFactoryBean.class,
        FreeMarkerConfigurer.class})
@AutoConfigureAfter(org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.class)

@EnableConfigurationProperties(FreemarkerProperties.class)
public class FreemarkerAutoConfiguration {

    private FreemarkerProperties freemarkerProperties;

    public FreemarkerAutoConfiguration(FreemarkerProperties freemarkerProperties) {
        this.freemarkerProperties = freemarkerProperties;
    }

    /**
     * 属性配置类
     */
    @ConditionalOnClass(FreeMarkerConfigurationFactory.class)
    protected static class FreemarkerConfiguration {

        // TODO 验证 静态类 如果不使用 autowired 注解，是否能使用外部的bean对象
        @Autowired
        private FreemarkerProperties properties;

        public void applyProperties(FreeMarkerConfigurationFactory factory) {
            factory.setTemplateLoaderPaths(this.properties.getTemplateLoaderPaths());
            factory.setDefaultEncoding(this.properties.getCharsetName());
            factory.setPreferFileSystemAccess(this.properties.getPreferFileSystemAccess());
            Properties properties = new Properties();
            properties.putAll(this.properties.getProperties());
            factory.setFreemarkerSettings(properties);
        }

    }

    /**
     * not web Freemarker BEAN
     */
    @Configuration
    @ConditionalOnNotWebApplication
    protected static class FreeMarkerConfigurationFactoryBeanFactory extends FreemarkerConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public FreeMarkerConfigurationFactoryBean create() {
            FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
            applyProperties(bean);
            return bean;
        }
    }

    /**
     * freemarker config in web environment
     */
    @Configuration
    @ConditionalOnWebApplication
    protected static class FreeMarkerConfigurerFactory extends FreemarkerConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public FreeMarkerConfigurer create() {
            FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
            applyProperties(freeMarkerConfigurer);
            return freeMarkerConfigurer;
        }

        @Bean
        @ConditionalOnMissingBean
        public freemarker.template.Configuration createConfiguration(FreeMarkerConfigurer freeMarkerConfigurer) {
            return freeMarkerConfigurer.getConfiguration();
        }

    }

}
