package com.jin.web.security.service;

import com.jin.web.security.entity.UserAccount;

public interface UserAccountService {

	
	public UserAccount retrieveUser(String username, String password);

}
