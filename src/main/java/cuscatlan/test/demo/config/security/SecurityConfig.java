package cuscatlan.test.demo.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cuscatlan.test.demo.model.entity.ClientsEntity;
import cuscatlan.test.demo.repositories.ClientsRepository;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain springWebFilterChain(HttpSecurity http, JwtUtil tokenProvider) throws Exception {
		return http.httpBasic(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers("/clients/**", "/v3/api-docs/**", "/swagger-ui/**")
								.permitAll().anyRequest().authenticated())
				.addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider),
						UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	UserDetailsService customUserDetailsService(ClientsRepository users) {
		return username -> {
			ClientsEntity user = users.findByEmail(username);
			List<SimpleGrantedAuthority> roles = null;
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getEmail(), user.getPassword(), roles);
		};
	}

	@Bean
	AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService) {
		return authentication -> {
			String username = authentication.getPrincipal() + "";

			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
		};

	}

}
