package com.blogsweb.web.blogsweb.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @类名: SwaggerConfiguration
 * @包名: com.blogs.web.blogsweb.config
 * @IDE的名称: IntelliJ IDEA
 * @当前项目的名称: blogsweb
 * @作者: 杨冕
 * @时间: 2020/5/12 14:31
 * @版本: 1.0.0
 * <p>说明: </p>
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)      // 选择swagger2版本
                .apiInfo(apiInfo())         //定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.blogsweb.web.blogsweb.web"))  // 指定生成api文档的包
                .paths(PathSelectors.any())     // 指定所有路径
                .build()
                ;
    }

    /**
     * 构建文档api信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("个人博客")     // 文档标题
                .contact(new Contact("Yang.M & JiaJiWei", "http://localhost:8080", "1477699332@qq.com"))   //联系人信息
                .description("系统架构采用SpringBoot2.x+shiro+mybatis+redis+jedis+knife4j+vue的基础框架以及Ftp+Nginx静态资源服务器")      //描述
                .version("1.0.1")     //文档版本号
                .termsOfServiceUrl("http://localhost:8080")     //网站地址
                .build();
    }
}