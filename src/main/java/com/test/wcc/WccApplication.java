package com.test.wcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(
		basePackages = {
				"com.test.wcc.*"
		}
)
@ComponentScan(basePackages = { "com.test.wcc.*" })
@EntityScan(basePackages = { "com.test.wcc.*"})
public class WccApplication {

	public static void main(String[] args) {
		SpringApplication.run(WccApplication.class, args);
	}

}
