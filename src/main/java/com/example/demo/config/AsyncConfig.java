package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.demo.non_entity.GlobalValue;
import com.example.demo.non_entity.GlobalValueThreadLocal;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

	@Autowired
	@Lazy
	GlobalValueThreadLocal globalValueThreadLocal;

	@Autowired
	@Lazy
	GlobalValue globalValue;

	@Bean("asyncTaskExecuter")
	public ThreadPoolTaskExecutor asyncTaskExecuter() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(20);
		taskExecutor.setQueueCapacity(500);
		taskExecutor.setMaxPoolSize(20);
		taskExecutor.setThreadNamePrefix("task-thread-exec-");
		taskExecutor.initialize();

		// Add the TaskDecorator to automatically handle ThreadLocal
		taskExecutor.setTaskDecorator(new ThreadLocalTaskDecorator(globalValueThreadLocal));

		return taskExecutor;
	}
}
