package com.example.internalcache.config;


import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CacheConfiguration {

    @Value("${application.cache.ttl}")
    private int ttl;

    @Bean
    CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();

        caffeineCacheManager.registerCustomCache("checkResult", Caffeine.newBuilder()
                .expireAfterAccess(ttl, TimeUnit.SECONDS).build());
        caffeineCacheManager.registerCustomCache("filmData", Caffeine.newBuilder()
                .expireAfterAccess(ttl, TimeUnit.SECONDS).build());
        return caffeineCacheManager;
    }
}
