package com.poly.DATN_BookWorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.servlet.annotation.WebServlet;

@SpringBootApplication
@Configuration
@EnableWebSecurity
public class DatnBookWormsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatnBookWormsApplication.class, args);
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
}
