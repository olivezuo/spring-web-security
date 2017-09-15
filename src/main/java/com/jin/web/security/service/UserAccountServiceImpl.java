package com.jin.web.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.web.security.client.SiteMinderClient;
import com.jin.web.security.entity.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	@Autowired
	SiteMinderClient siteMinderClient;

	public UserAccount retrieveUser(String username, String password){
		try {
			UserAccount userAccount = siteMinderClient.login(username, password);
			return userAccount;
		} catch (Exception e) {
			logger.error("We encounter a siteminder issue: " + e.getLocalizedMessage());
			return null;
		}
	}

}
