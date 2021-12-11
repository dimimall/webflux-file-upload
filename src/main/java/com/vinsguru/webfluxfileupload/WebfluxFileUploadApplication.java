package com.vinsguru.webfluxfileupload;
import io.r2dbc.spi.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication
//@EnableR2dbcRepositories
public class WebfluxFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxFileUploadApplication.class, args);
	}
}
