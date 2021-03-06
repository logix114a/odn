package com.noblens.odn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.noblens.odn.forest.misc.StorageService;
import com.noblens.odn.forest.misc.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class OdnApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdnApplication.class, args);
	}
	
	  @Bean
	    CommandLineRunner init(StorageService storageService) {
	        return (args) -> {
	            storageService.deleteAll();
	            storageService.init();
	        };
	    }
}
