package com.poly.DATN_BookWorms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	Phân quyền sử dụng

	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((request) -> request
				.requestMatchers("/account/**", "/product/**", "/Admin/Css/**", "/Admin/Image/**", "/Admin/Js/**")
				.permitAll().requestMatchers("/Client/Css/**", "/Client/Image/**", "/Client/Js/**").permitAll()
				.requestMatchers("static/**").permitAll().requestMatchers("/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/seller/**").hasAuthority("SELLER").anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/account/login").loginProcessingUrl("/account/login")
				.defaultSuccessUrl("/product/a").permitAll())
				.logout((form) -> form.logoutUrl("/account/logout").logoutSuccessUrl("/account/logoutSuccess")
				.permitAll());

		return http.build();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
