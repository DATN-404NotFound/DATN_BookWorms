package com.poly.DATN_BookWorms.config;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//	Phân quyền sử dụng
	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/account/**", "/signin/**", "/signup/**", "/product/**", "/Admin/Css/**", "/Admin/Image/**", "/Admin/Js/**","/Ibook/**")
				.permitAll().requestMatchers("rest/**").permitAll()
                .requestMatchers("/Client/**")
				.permitAll()
                .requestMatchers("static/**")
				.permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/seller/**").hasAuthority("SELLER")
				.anyRequest().authenticated());
        http.formLogin(form -> form.loginPage("/account/login")
				.loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/Ibook/index")
				.permitAll());

        http.oauth2Login(customize -> customize.loginPage("/account/login")
				.defaultSuccessUrl("/account/login-google/success")
				.failureUrl("/account/login")
				.authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
						.baseUri("/oauth2/authorization"))
				.permitAll());

		http.logout((form) -> form
				.logoutUrl("/account/logout")
				.logoutSuccessUrl("/account/login")
				.permitAll());

		http.cors().and().csrf().disable();
		return http.build();
	}

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
