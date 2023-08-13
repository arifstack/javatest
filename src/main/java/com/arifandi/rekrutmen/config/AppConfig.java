package com.arifandi.rekrutmen.config;

import com.arifandi.rekrutmen.security.util.JWTTokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Key;

@Configuration
public class AppConfig {
	
	@Bean
	public Key key() {
		byte[] keyBytes = Decoders.BASE64.decode("lkqelwklkdalldsakakdsqw9wqe98ewq9pqwe989089821kjkwjqekl98kljaksdjklsdaj");
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	@Bean
	public JWTTokenFactory jwtTokenFactory(Key key) {
		return new JWTTokenFactory(key);
	}
 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
 
}