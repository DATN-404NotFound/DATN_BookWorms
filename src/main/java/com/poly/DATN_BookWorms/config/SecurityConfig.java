package com.poly.DATN_BookWorms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import com.poly.DATN_BookWorms.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	AccountService accountservice;
//	Phân quyền sử dụng

	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((request) -> request
				.requestMatchers("/account/**", "/Admin/Css/**", "/Admin/Image/**", "/Admin/Js/**")

				.permitAll().requestMatchers("/Client/**", "/product/**").permitAll().requestMatchers("/rest/**")
				.permitAll().requestMatchers("static/Client/**").permitAll().requestMatchers("").hasAuthority("ADMIN")
				.requestMatchers("/seller/**").hasAuthority("SELLER").anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/account/login").loginProcessingUrl("/account/login")

						.defaultSuccessUrl("/product/a", false).permitAll())

				.logout((form) -> form.logoutUrl("/account/logout").logoutSuccessUrl("/account/logoutSuccess")

						.permitAll());
		http.cors().and().csrf().disable();

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
