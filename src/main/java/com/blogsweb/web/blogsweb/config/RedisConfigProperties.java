package com.blogsweb.web.blogsweb.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * redis 配置属性类
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {
    /**
     * Redis服务器地址
     */
    private String host;
    /**
     * Redis服务器连接密码（默认为空）
     */
    private String password;
    /**
     * Redis数据库索引（默认为0）
     */
    private int database;
    /**
     * Redis服务器连接端口
     */
    private int port;
    /**
     * 连接超时时间（毫秒）
     */
    private int timeout;
    //省略 get/set方法


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}