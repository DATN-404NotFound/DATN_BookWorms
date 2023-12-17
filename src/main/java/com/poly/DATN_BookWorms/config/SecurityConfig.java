package com.poly.DATN_BookWorms.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poly.DATN_BookWorms.repo.ViewWebRepo;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer{
	
	@Autowired
	ViewWebRepo webRepo;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ViewCount(webRepo));
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RedirectStrategy redirectStrategy() {
		return new DefaultRedirectStrategy() {
			public String getLocation(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
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
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				redirectStrategy().sendRedirect(request, response, "/Ibook/index");
			}
		};
	}
	//	Phân quyền sử dụng
	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((request) -> request
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
				.defaultSuccessUrl("/account/login/success")
				.failureUrl("/account/login")
				.authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
						.baseUri("/oauth2/authorization"))
				.successHandler((request, response, authentication) -> {
			        // Xác định OAuth2 dựa trên thông tin xác thực
					String redirectUrl = "/account/login/success/";

				    // Kiểm tra nhà cung cấp OAuth2 từ thông tin xác thực
				    if (authentication.getAuthorities().stream()
				            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("google"))) {
				        redirectUrl += "google";
				    } else {
				        // Xử lý những nhà cung cấp khác nếu cần
				        redirectUrl += "facebook";
				    }

				    response.sendRedirect(redirectUrl);
			    })
				.permitAll());

		http.logout((form) -> form
				.logoutUrl("/account/logout")
				.logoutSuccessUrl("/account/login")
				.permitAll());

		http.cors().and().csrf().disable();
		return http.build();
	}
	
	 public static String determineOAuth2Provider(Authentication authentication) {
	        // Kiểm tra thông tin xác thực để xác định nhà cung cấp OAuth2
	        if (authentication.getAuthorities().stream()
	                .anyMatch(grantedAuthority ->
	                        grantedAuthority.getAuthority().equals("ROLE_GOOGLE_USER"))) {
	            return "google";
	        } else if (authentication.getAuthorities().stream()
	                .anyMatch(grantedAuthority ->
	                        grantedAuthority.getAuthority().equals("ROLE_FACEBOOK_USER"))) {
	            return "facebook";
	        } else {
	            // Xử lý những trường hợp khác nếu cần
	            return "unknown";
	        }
	    }

}
