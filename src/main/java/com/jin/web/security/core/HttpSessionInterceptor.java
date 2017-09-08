package com.jin.web.security.core;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HttpSessionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HttpSession session = request.getSession(false);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean val = request.isRequestedSessionIdValid();
		Cookie[] cookie = request.getCookies();

		HttpSession session = request.getSession(false);
		
		//SecurityContextImpl a = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		//SecurityContextHolder.getContext().getAuthentication();
		return true;
	}

}
