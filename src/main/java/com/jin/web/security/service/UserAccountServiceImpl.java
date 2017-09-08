package com.jin.web.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.web.security.client.SiteMinderClient;
import com.jin.web.security.entity.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	SiteMinderClient siteMinderClient;

	public UserAccount retrieveUser(String username, String password){
		
		UserAccount userAccount = siteMinderClient.login(username, password);
		
		return userAccount;
	}

}
