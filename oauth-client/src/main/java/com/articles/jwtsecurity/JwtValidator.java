package com.articles.jwtsecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.articles.domain.JwtUser;

@Component
public class JwtValidator {
	   private static final Logger log = LoggerFactory.getLogger(JwtValidator.class);
	private String secret = "secret";
	
	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
        	log.warn("JWT Validation failed : {}", e.getMessage());
        }

        return jwtUser;
		
	}

}
