package com.example.week7.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {


    @Bean
    public CacheManager cacheManager() {
        // "likes"라는 이름의 캐시를 사용할 수 있게 등록
        return new ConcurrentMapCacheManager("likes");
    }

}
