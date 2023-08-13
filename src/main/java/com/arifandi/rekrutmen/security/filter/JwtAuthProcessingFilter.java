package com.arifandi.rekrutmen.security.filter;

import com.arifandi.rekrutmen.security.model.AnonymousAuthentication;
import com.arifandi.rekrutmen.security.model.JwtAuthenticationToken;
import com.arifandi.rekrutmen.security.model.RawAccessJwtToken;
import com.arifandi.rekrutmen.util.TokenExtractor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;

public class JwtAuthProcessingFilter extends AbstractAuthenticationProcessingFilter{
	
	private final TokenExtractor tokenExtractor;
	
	private final AuthenticationFailureHandler failureHandler;

	public JwtAuthProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			TokenExtractor tokenExtractor,
			AuthenticationFailureHandler failureHandler) {
		super(requiresAuthenticationRequestMatcher);
		this.tokenExtractor = tokenExtractor;
		this.failureHandler = failureHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String authorizationHeader = request.getHeader("Authorization");
		String jwt = tokenExtractor.extract(authorizationHeader);
		RawAccessJwtToken rawToken = new RawAccessJwtToken(jwt);
		return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(rawToken));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());
		failureHandler.onAuthenticationFailure(request, response, failed);

	}
	
	

}
