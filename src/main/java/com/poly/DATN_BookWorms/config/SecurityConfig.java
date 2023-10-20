package com.poly.DATN_BookWorms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	Phân quyền sử dụng

	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/account/**,/product/**,/Admin/**,/Client/**,/static/**").permitAll()
			.requestMatchers("/admin/**").hasAuthority("ADMIN")
			.requestMatchers("/seller/**").hasAuthority("SELLER")
			.requestMatchers("/product/**").hasAuthority("GUEST")
			.anyRequest().authenticated());
		http.formLogin(form ->form.loginPage("/account/login").loginProcessingUrl("/account/login")
				.defaultSuccessUrl("/a", false).failureUrl("/account/login/error").permitAll());
		http.rememberMe().tokenValiditySeconds(86400);
		
		http.exceptionHandling().accessDeniedPage("/account/unauthoried");
		
		http.logout().logoutUrl("/account/logoff").logoutSuccessUrl("/security/logoff/success");
		
				// Login With MXH
//		http.oauth2Login().loginPage("/account/login/form").defaultSuccessUrl("/oauth2/login/success", true)
//						.failureUrl("/account/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");	
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(User.withUsername("user")
	      .password(bCryptPasswordEncoder.encode("userPass"))
	      .roles("USER")
	      .build());
	    manager.createUser(User.withUsername("admin")
	      .password(bCryptPasswordEncoder.encode("adminPass"))
	      .roles("USER", "ADMIN")
	      .build());
	    return manager;
	}
	
//	//Remember Me
//	@Bean
//	RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
//		RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
//		TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices(null, userDetailsService, encodingAlgorithm);
//		rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
//		return rememberMe; 
//	}
	
//	@Bean
//	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
//			PasswordEncoder passwordEncoder) {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//		return new ProviderManager(authenticationProvider);
//	}
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails userDetails = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(userDetails);
//	}
//
//	@Autowired
//	public void configure(AuthenticationManagerBuilder builder) {
//		builder.eraseCredentials(false);
//	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	//Cung cấp nguồn dữ liệu đăng nhập
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(username -> {
//			try {
//				Account user = accountService.findById(username);
//				String password = pe.encode(user.getPassword());
//				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId())
//						.collect(Collectors.toList()).toArray(new String[0]);
//				return User.withUsername(username).password(password).roles(roles).build();
//			} catch (NoSuchElementException e) {
//				// TODO: handle exception
//				throw new UsernameNotFoundException(username + " not found!");/			}
//		});
//	}
//
//
//
//
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//	}

}
