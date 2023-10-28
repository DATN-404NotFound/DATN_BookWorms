
package com.poly.DATN_BookWorms;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication

//@EnableWebSecurity
public class DatnBookWormsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatnBookWormsApplication.class, args);
	}
	

}
