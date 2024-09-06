package com.omer.sakila.movimo.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.service.CustomerService;

@Configuration
public class SpringSecurityConfiguration {

	@Autowired
	private CustomerService customerService;
	
	public SpringSecurityConfiguration(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		List<UserDetails> users = new ArrayList<UserDetails>();
		List<Customer> customers = customerService.getAllCustomers();
		
		for (Customer customer : customers) {
			users.add(createNewUser(customer.getEmail(), customer.getPassword()));
		}
		
		return new InMemoryUserDetailsManager(users); 
	}
	
	private UserDetails createNewUser(String email, String password) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
				.passwordEncoder(passwordEncoder)
				.username(email)
				.password(password)
				.roles("USER")
				.build();
		
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		
		http.csrf(csrf -> csrf.disable());

		http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
		
		return http.build();
	}
}
