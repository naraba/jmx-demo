package com.example.jmx_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    public static final int THREAD_CHILD_POOL_SIZE = 10;
    public static final String THREAD_NAME_PREFIX = "Child_";

    @Bean
    ThreadPoolTaskExecutor configChildren() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(THREAD_CHILD_POOL_SIZE);
        threadPool.setMaxPoolSize(THREAD_CHILD_POOL_SIZE);
        threadPool.setQueueCapacity(0);
        threadPool.setDaemon(true);
        threadPool.setThreadNamePrefix(THREAD_NAME_PREFIX);
        threadPool.initialize();
        return threadPool;
    }
}
