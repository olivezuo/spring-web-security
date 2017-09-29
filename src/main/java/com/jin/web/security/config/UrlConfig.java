package com.jin.web.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="com.jin.web.security.url")
public class UrlConfig {

	private String noAuthWebUrlsPattern = "/, /home";
	
	private String loginUrl = "/login";
	
	private String apiUrlPrefix = "/api";


	public String getNoAuthWebUrlsPattern() {
		return noAuthWebUrlsPattern;
	}

	public void setNoAuthWebUrlsPattern(String noAuthWebUrlsPattern) {
		this.noAuthWebUrlsPattern = noAuthWebUrlsPattern;
	}

	public String getApiUrlPrefix() {
		return apiUrlPrefix;
	}

	public void setApiUrlPrefix(String apiUrlPrefix) {
		this.apiUrlPrefix = apiUrlPrefix;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	
	
}
