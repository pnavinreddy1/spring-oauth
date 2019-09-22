package com.oauth.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@SpringBootApplication
@EnableResourceServer
public class OauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

	@Configuration
	@Order(1)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	    @Value("${user.oauth.user.username}")
	    private String username;
	    @Value("${user.oauth.user.password}")
	    private String password;
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.requestMatchers()
	            .antMatchers("/login", "/oauth/authorize")
	            .and()
	            .authorizeRequests()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().permitAll();
	    }
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	            .withUser(username)
	            .password(passwordEncoder().encode(password))
	            .roles("USER");
	    }
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthServerConfig extends AuthorizationServerConfigurerAdapter {		
		@Value("${user.oauth.clientId}")
	    private String ClientID;
	    @Value("${user.oauth.clientSecret}")
	    private String ClientSecret;
	    @Value("${user.oauth.redirectUris}")
	    private String RedirectURLs;
	  
	    private final PasswordEncoder passwordEncoder;

	   public AuthServerConfig(PasswordEncoder passwordEncoder) {
	        this.passwordEncoder = passwordEncoder;
	    }

	   
	   @Override
	    public void configure(
	        AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer.tokenKeyAccess("permitAll()")
	            .checkTokenAccess("isAuthenticated()");
	    }
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.inMemory()
	            .withClient(ClientID)
	            .secret(passwordEncoder.encode(ClientSecret))
	            .authorizedGrantTypes("authorization_code")
	            .scopes("user_info")
	            .autoApprove(true)
	            .redirectUris(RedirectURLs);
	    }

	}
}
