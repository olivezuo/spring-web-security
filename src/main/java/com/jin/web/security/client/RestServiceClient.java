package com.jin.web.security.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@ConfigurationProperties(prefix="com.jin.restClient")
public class RestServiceClient {
	
	protected String username;
	protected String password;
	protected String protocol = "https";
	protected String host;
	protected int port = 443;
	protected String appRoot ="";
	protected boolean requireAuth = false;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	protected RestTemplate restTemplate;
	
	@PostConstruct
	protected void initRestTemplate () {
		if (requireAuth) {
			restTemplate = restTemplateBuilder.basicAuthorization(getUsername(), getPassword())
														.rootUri(buildRootUri())
														.build();
		} else {
			restTemplate = restTemplateBuilder.rootUri(buildRootUri()).build();
		}
	}

	protected String buildRootUri() {
		String baseUri = getProtocol() + "://"+getHost() + ":" + getPort() + "/" + getAppRoot();
		
		return baseUri;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAppRoot() {
		return appRoot;
	}
	public void setAppRoot(String appRoot) {
		this.appRoot = appRoot;
	}


}
