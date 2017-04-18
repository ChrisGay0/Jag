package com.jag.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot configuration file
 * @author Computer
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.jag"})
public class JagexConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(JagexConfiguration.class, args);
	}

}