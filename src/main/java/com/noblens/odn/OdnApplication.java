package com.noblens.odn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.noblens.odn.forest.misc.StorageService;

@SpringBootApplication
public class OdnApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdnApplication.class, args);
	}
	
	
}
