package com.infosys.Employee_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//@EnableAuthorizationServer
//@EnableResourceServer
@SpringBootApplication(scanBasePackages="com.infosys.Employee_final")
@PropertySource(value = {"classpath:configuration.properties"})
public class EmployeeFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeFinalApplication.class, args);
	}
}
