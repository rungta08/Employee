package com.infosys.Employee_final_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EmployeeFinalConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeFinalConfigServerApplication.class, args);
	}
}
