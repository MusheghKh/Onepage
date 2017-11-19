package com.max.onepage;

import com.max.onepage.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class OnepageApplication{


	public static void main(String[] args) {
		SpringApplication.run(OnepageApplication.class, args);
	}

}
