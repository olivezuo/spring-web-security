package com.jin.web.security.client;

import java.util.Arrays;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.jin.web.security.entity.UserAccount;
import com.jin.web.security.entity.siteminder.LoginResponse;

@Component
@ConfigurationProperties(prefix="com.jin.siteminder.restClient")
public class SiteMinderClient extends RestServiceClient{
	
	
	public UserAccount login(String userName, String password) {
		String uri = "/login";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setCacheControl("no-cache");

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("username", userName);
		map.add("password", password);

		HttpEntity<MultiValueMap<String, String>> loginRequest = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<LoginResponse> response = restTemplate.postForEntity( uri, loginRequest , LoginResponse.class );
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(userName);
		userAccount.setPassword("HidePass");
		userAccount.setLoginResponse(response.getBody());
		
		if (response.getBody().getReturnCode().intValue() == 1) {
			userAccount.setValid(true); 
		}
				
		return userAccount;
	}

}
