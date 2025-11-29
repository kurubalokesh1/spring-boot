package com.Spring.PgManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication
public class SpringBootPgManagementSystemApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext container =	SpringApplication.run(SpringBootPgManagementSystemApplication.class, args);
	
	}

}
