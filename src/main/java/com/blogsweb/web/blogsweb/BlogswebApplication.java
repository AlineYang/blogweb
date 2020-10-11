package com.blogsweb.web.blogsweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching // 开启缓存
@EnableTransactionManagement // 开启事务，保证redis与mysql中数据的一致性
public class BlogswebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogswebApplication.class, args);
    }

}
