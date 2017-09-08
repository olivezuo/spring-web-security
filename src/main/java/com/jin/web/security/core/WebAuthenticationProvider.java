package com.jin.web.security.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jin.web.security.entity.UserAccount;
import com.jin.web.security.service.UserAccountService;

@Component
public class WebAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(WebAuthenticationProvider.class);
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			logger.error("Username {}: no password provided", username);
		    throw new BadCredentialsException("Please enter password");
		}
		
		UserAccount userAccount = userAccountService.retrieveUser(username, password);
		
		if (!userAccount.isValid()) {
        	logger.error("Username {} password {}: invalid username/password combination", username, password);
            throw new BadCredentialsException("Invalid Login");

		}
		
		List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("NONE");
		return new WebUserDetails(userAccount, auths);
	}
	

}
