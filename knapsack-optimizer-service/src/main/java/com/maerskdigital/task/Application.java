package com.maerskdigital.task;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.maerskdigital.task.optimizer.DynamicPorgrammingKnapsackOptimizer;
import com.maerskdigital.task.optimizer.KnapsackOptimizer;

@SpringBootApplication
@EnableAsync
@EnableMongoRepositories
public class Application extends AsyncConfigurerSupport {

	public static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(Application.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("KnapsackSolver-");
		executor.initialize();
		return executor;
	}

	@Bean
	public KnapsackOptimizer optimizer() {
		return new DynamicPorgrammingKnapsackOptimizer();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();

	}

}
