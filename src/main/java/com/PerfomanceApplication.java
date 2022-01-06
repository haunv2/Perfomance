package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
public class PerfomanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerfomanceApplication.class, args);
    }

    @Bean
    @Primary
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(60);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.setCorePoolSize(30);
        return threadPoolTaskExecutor;
    }
}
