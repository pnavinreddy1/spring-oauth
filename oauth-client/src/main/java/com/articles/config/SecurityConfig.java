package com.articles.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.articles.jwtsecurity.JwtAuthenticationProvider;
import com.articles.jwtsecurity.JwtAuthenticationTokenFilter;
import com.articles.jwtsecurity.JwtFailureHandler;
import com.articles.jwtsecurity.JwtSuccessHandler;

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private JwtAuthenticationProvider  authenticationProvider;

		@Bean
		public AuthenticationManager authenticationManager() {	
			return new ProviderManager(Collections.singletonList(authenticationProvider));
		}
		
		@Bean
		public JwtAuthenticationTokenFilter authenticationTokenFilter() {
			JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
			filter.setAuthenticationManager(authenticationManager());
	        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
	        filter.setAuthenticationFailureHandler(new JwtFailureHandler());
	        return filter;
		}
		
		
	    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/cms/v1/token**").permitAll().and()
				.antMatcher("/**").authorizeRequests()
				.anyRequest().authenticated().and().oauth2Login();

		http.headers().cacheControl();
	}
	 
	}

