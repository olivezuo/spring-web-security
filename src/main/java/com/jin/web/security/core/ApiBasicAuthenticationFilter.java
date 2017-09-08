package com.jin.web.security.core;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ApiBasicAuthenticationFilter extends  BasicAuthenticationFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiBasicAuthenticationFilter.class);
	
	private int sessionMaxInactiveInterval;

	public ApiBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		
	}
	
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
		logger.info("Successfully login for user: " + authResult.getName());
		request.getSession().setMaxInactiveInterval(getSessionMaxInactiveInterval());
	}
	
	public int getSessionMaxInactiveInterval() {
		return sessionMaxInactiveInterval;
	}

	public void setSessionMaxInactiveInterval(int sessionMaxInactiveInterval) {
		this.sessionMaxInactiveInterval = sessionMaxInactiveInterval;
	}

}
