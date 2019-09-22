package com.articles.jwtsecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.articles.domain.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter   {
	
	public JwtAuthenticationTokenFilter() {
		super("/cms/v1/articles/**");
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response)  {
		String header = request.getHeader("Authorization");


        if (header == null || !header.startsWith("Bearer ")) {
        	throw new AuthenticationCredentialsNotFoundException("JWT Token is missing");
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }
	
	 @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
	        super.successfulAuthentication(request, response, chain, authResult);
	        chain.doFilter(request, response);
	    }

	 @Override
	 protected void unsuccessfulAuthentication(HttpServletRequest request,
	                                           HttpServletResponse response,
	                                           AuthenticationException failed)
	         throws IOException, ServletException {
	     getFailureHandler().onAuthenticationFailure(request, response, failed);
	     }
	}
