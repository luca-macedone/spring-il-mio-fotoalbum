package org.lessons.java.photoalbum.db.auth;


import org.lessons.java.photoalbum.db.serv.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
			.requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/photos").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/photos/create").hasAuthority("USER")
			.requestMatchers("/photos/edit/**").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/photos/update/**").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/photos/delete/**").hasAuthority("USER")
			.requestMatchers("/categories").hasAuthority("ADMIN")
			.requestMatchers("/categories/create").hasAuthority("ADMIN")
			.requestMatchers("/categories/edit/**").hasAuthority("ADMIN")
			.requestMatchers("/categories/update/**").hasAuthority("ADMIN")
			.requestMatchers("/categories/delete/**").hasAuthority("ADMIN")
			.requestMatchers("/messages").hasAuthority("ADMIN")
			.requestMatchers("/**").permitAll()
			.and().formLogin().defaultSuccessUrl("/")
			.and().logout();
		
		return http.build();
	}
	
	@Bean
	UserService userDetailService() {
		return new UserService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
}
