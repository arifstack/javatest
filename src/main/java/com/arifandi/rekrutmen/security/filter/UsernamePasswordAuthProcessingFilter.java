package com.arifandi.rekrutmen.security.filter;

import com.arifandi.rekrutmen.dto.LoginRequestDTO;
import com.arifandi.rekrutmen.exception.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class UsernamePasswordAuthProcessingFilter extends AbstractAuthenticationProcessingFilter{
	
	private final ObjectMapper objectMapper;
	
	private final AuthenticationSuccessHandler successHandler;
	
	private final AuthenticationFailureHandler failureHandler;

	public UsernamePasswordAuthProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper, AuthenticationSuccessHandler successHandler, 
			AuthenticationFailureHandler failureHandler) {
		super(defaultFilterProcessesUrl);
		this.objectMapper = objectMapper;
		this.successHandler = successHandler;
		this.failureHandler = failureHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		LoginRequestDTO dto = objectMapper.readValue(request.getReader(), LoginRequestDTO.class);
		if(StringUtils.isBlank(dto.username()) || StringUtils.isBlank(dto.password())) {
			throw new BadRequestException("username.password.shouldbe.provided");
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
		return this.getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		this.successHandler.onAuthenticationSuccess(request, response, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		this.failureHandler.onAuthenticationFailure(request, response, failed);
	}
	
	

}
