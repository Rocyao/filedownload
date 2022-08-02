package com.tj.filedownload.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author peng
 * @createDate 2022/8/2 11:26
 */
@Configuration
public class CaffeineConfig<T> {
    @Bean
    public Cache<String, T> CaffeineConfig(){
        return Caffeine.newBuilder()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .initialCapacity(10)
                .maximumSize(100)
                .build();
    }
}
