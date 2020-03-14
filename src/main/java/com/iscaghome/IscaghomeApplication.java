package com.iscaghome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = "com.iscaghome.model")
public class IscaghomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IscaghomeApplication.class, args);
	}

}
