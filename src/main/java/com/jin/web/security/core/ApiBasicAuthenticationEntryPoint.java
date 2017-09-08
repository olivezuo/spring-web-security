package com.jin.web.security.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class ApiBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiBasicAuthenticationEntryPoint.class);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 * We remove the annonymous session for failed authentication request
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
			throws IOException, ServletException {
		logger.info("Not authenticated Api request: " + request.getRequestURI());
		super.commence(request, response, authException);
		HttpSession session = request.getSession(false);
		if ( null != session) {
			session.invalidate();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("jin");
		super.afterPropertiesSet();
	}
}
