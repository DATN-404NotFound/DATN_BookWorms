package com.poly.DATN_BookWorms.config;

<<<<<<< HEAD
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> zendyy/back_end
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
<<<<<<< HEAD
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import java.io.IOException;
import java.util.List;
=======

import com.poly.DATN_BookWorms.service.AccountService;
>>>>>>> zendyy/back_end

@Configuration
@EnableWebSecurity
public class SecurityConfig {
<<<<<<< HEAD
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RedirectStrategy redirectStrategy() {
		return new DefaultRedirectStrategy() {
			public String getLocation(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) {
				String previousUrl = request.getHeader("Referer");
				if (previousUrl != null) {
					return previousUrl;
				} else {
					return "/Ibook/index";
				}
			}
		};
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				redirectStrategy().sendRedirect(request, response, "/Ibook/index");
			}
		};
	}


	// Phân quyền sử dụng
	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
		
		http
	.authorizeHttpRequests((request) -> request
				
				.requestMatchers("/account/**", "/signin/**", "/signup/**", "/product/**", "/Admin/**","/Ibook/index","/Ibook/header")
				.permitAll().requestMatchers("rest/**").permitAll()
				.requestMatchers("/Client/**")
				.permitAll()
				.requestMatchers("static/**")
				.permitAll()
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().authenticated());
		http.formLogin(form -> form.loginPage("/account/login")
				.loginProcessingUrl("/account/login")
				.successHandler(successHandler())
				.permitAll());

		http.oauth2Login(customize -> customize.loginPage("/account/login")
				.defaultSuccessUrl("/account/login-google/success").defaultSuccessUrl("/account/login-facebook/success")
				.failureUrl("/account/login")
				.authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
						.baseUri("/oauth2/authorization"))
				.permitAll());

		http.logout((form) -> form
				.logoutUrl("/account/logout")
				.logoutSuccessUrl("/account/login")
				.permitAll());


    
		http.cors().disable().csrf().disable();
		return http.build();
	}
	

=======

	@Autowired
	AccountService accountservice;
//	Phân quyền sử dụng

	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((request) -> request
				.requestMatchers("/account/**", "/Admin/Css/**", "/Admin/Image/**", "/Admin/Js/**")

				.permitAll().requestMatchers("/Client/**", "/product/**").permitAll().requestMatchers("/rest/**")
				.permitAll().requestMatchers("static/Client/**").permitAll().requestMatchers("admin/**", "api/payment/**").hasAuthority("ADMIN")
				.requestMatchers("/seller/**").hasAuthority("SELLER").anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/account/login").defaultSuccessUrl("/product/dashboard").permitAll())

				.logout((form) -> form.logoutUrl("/account/logout").logoutSuccessUrl("/account/logoutSuccess")

						.permitAll());
		http.cors().and().csrf().disable();

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

>>>>>>> zendyy/back_end
}
