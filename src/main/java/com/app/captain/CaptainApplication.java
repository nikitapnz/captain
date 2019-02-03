package com.app.captain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class CaptainApplication {
	public static void main(String[] args) {
		SpringApplication.run(CaptainApplication.class, args);
	}

	@Bean(name = "uniqueObjectForParticipant")
	public Map<Long, Object> uniqueObjectForParticipant() {
		return new ConcurrentHashMap<>();
	}
}

