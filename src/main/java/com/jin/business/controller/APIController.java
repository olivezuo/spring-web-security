package com.jin.business.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jin.web.security.core.WebUserDetails;

@RestController
public class APIController {

	@RequestMapping("/api/user")
	public String user(UsernamePasswordAuthenticationToken user, HttpServletResponse response) {
		
		//response.addCookie(new Cookie("SMSESSION", user.getName()));
		return ((WebUserDetails)user.getPrincipal()).getUserAccount().getPassword();
	}

	@RequestMapping("/api/readme")
	public String readme(HttpServletResponse response) {

		//response.addCookie(new Cookie("SMSESSION","anything"));
		return "NO good";
	}

}
