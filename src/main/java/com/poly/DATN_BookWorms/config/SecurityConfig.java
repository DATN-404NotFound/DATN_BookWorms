package com.poly.DATN_BookWorms.config;


import com.poly.DATN_BookWorms.service.CustomUserDetailService;
import com.poly.DATN_BookWorms.service.FacebookConnectionSignup;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FacebookConnectionSignup facebookConnectionSignup;


	@Value("${spring.social.facebook.appSecret}")
	String appSecret;

	@Value("${spring.social.facebook.appId}")
	String appId;

	//	Phân quyền sử dụng
	@Bean
//	@Order(2)
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((request) -> request
				.requestMatchers("/account/**","/signin/**","/signup/**", "/product/**", "/Admin/Css/**", "/Admin/Image/**", "/Admin/Js/**").permitAll()
				.requestMatchers("/Client/Css/**", "/Client/Image/**", "/Client/Js/**").permitAll()
				.requestMatchers("static/**").permitAll()
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/seller/**").hasAuthority("SELLER").anyRequest().authenticated())
			.formLogin(form -> form.loginPage("/account/login").loginProcessingUrl("/account/login")
				.defaultSuccessUrl("/product/a").permitAll())
			.logout((form) -> form.logoutUrl("/account/logout").logoutSuccessUrl("/account/login")
				.permitAll());
//		.oauth2Login(oauthCustomize -> oauthCustomize
//				.loginProcessingUrl("/account/login")
//				.loginPage("/oauth2/authorization/google")
//				.successHandler(new AuthenticationSuccessHandler() {
//					@Override
//					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//						request.authenticate(response);
//					}
//				}).failureHandler(new AuthenticationFailureHandler() {
//					@Override
//					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//
//					}
//				}));
		return http.build();
	}
	@Bean
	public ProviderSignInController providerSignInController() {
		ConnectionFactoryLocator connectionFactoryLocator =
				connectionFactoryLocator();
		UsersConnectionRepository usersConnectionRepository =
				getUsersConnectionRepository(connectionFactoryLocator);
		((InMemoryUsersConnectionRepository) usersConnectionRepository)
				.setConnectionSignUp(facebookConnectionSignup);
		return new ProviderSignInController(connectionFactoryLocator,
				usersConnectionRepository, new FacebookSignInAdapter());
	}

	private ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
		return registry;
	}

	private UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator
																		   connectionFactoryLocator) {
		return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
	}
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManager build = http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.and()
				.build();
		return build;
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
