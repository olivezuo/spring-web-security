package com.jin.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.jin.web.security.core.ApiBasicAuthenticationEntryPoint;
import com.jin.web.security.core.ApiBasicAuthenticationFilter;
import com.jin.web.security.core.FormAuthenticationSuccessHandler;
import com.jin.web.security.core.WebAuthenticationProvider;

@EnableWebSecurity
public class MultiHttpSecurityConfig {
	
	@Autowired
	private WebAuthenticationProvider webAuthenticationProvider;
	
	@Autowired
	private FormAuthenticationSuccessHandler formAuthenticationSuccessHandler;

	@Autowired
	HttpSessionConfig httpSessionConfig;
	
	@Autowired
	ApiBasicAuthenticationEntryPoint apiBasicAuthenticationEntryPoint;

	@Autowired
	UrlConfig urlConfig;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(webAuthenticationProvider);
    }

	
	@Configuration
    @Order(2)
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			String[] noAuthWebUrlsPattern = (urlConfig.getNoAuthWebUrlsPattern() + "," + urlConfig.getApiUrlPrefix() + "**").replaceAll("\\s+","").split(",");
	        http
	        .authorizeRequests()
	            .antMatchers(noAuthWebUrlsPattern).permitAll()
	        	//.antMatchers("/", "/home", "/api**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage(urlConfig.getLoginUrl())
	            .permitAll()
	            .successHandler(formAuthenticationSuccessHandler)
	            .and()
	        .logout()
	            .permitAll();
			
		}
	
	}

	@Configuration
    @Order(1)
	public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
	        http
	        .addFilterAt(apiBasicAuthenticationFilter(), BasicAuthenticationFilter.class)
	        .antMatcher(urlConfig.getApiUrlPrefix() + "/**")
	        .authorizeRequests()
	            .anyRequest().authenticated()
	            .and()
	        .httpBasic()
	        	.authenticationEntryPoint(apiBasicAuthenticationEntryPoint)
	        	.and()
	        .csrf()
	        	.disable();
	        //.sessionManagement()
	        	//.maximumSessions(1);			
		}

	   @Bean
	   protected ApiBasicAuthenticationFilter apiBasicAuthenticationFilter() throws Exception { 
			ApiBasicAuthenticationFilter filter = new ApiBasicAuthenticationFilter(authenticationManagerBean());
			
			filter.setSessionMaxInactiveInterval(httpSessionConfig.getMaxInactiveInterval());
	        return filter; 
	    } 

		
	}

	
}
