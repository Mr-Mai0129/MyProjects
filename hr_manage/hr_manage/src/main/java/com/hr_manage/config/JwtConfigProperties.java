package com.hr_manage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置jwt属性
 */
@Configuration
@ConfigurationProperties(value = "jwt")
public class JwtConfigProperties {

    //有效时间
    private int expire;
    //加密密钥
    private String secret;



    public long getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "JwtConfigProperties{" +
                "expire=" + expire +
                ", secret='" + secret + '\'' +
                '}';
    }
}
