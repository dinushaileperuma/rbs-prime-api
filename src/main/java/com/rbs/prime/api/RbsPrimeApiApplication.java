package com.rbs.prime.api;

import com.rbs.prime.api.util.ClockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.Clock;
import java.util.Arrays;

@SpringBootApplication
@EnableCaching
public class RbsPrimeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbsPrimeApiApplication.class, args);
	}

	@Bean
	public ClockService clockService() {
		return new ClockService(Clock.systemDefaultZone());
	}
}
