package com.articles.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import com.articles.domain.JwtUser;
import com.articles.jwtsecurity.JwtGenerator;

@Component
@Path("/v1")
@Api(value = "KnowledgeBase Article APIs", produces = "application/json")
public class RootResource {

	@Context
	ResourceContext resourceContext;
	
	@Path("/articles")
	public ArticlesBaseResource getArticlesBaseResource() {
		return resourceContext.getResource(ArticlesBaseResource.class);
	}
	
	@POST
	@PermitAll
	@Path("/token")
	@ApiOperation(value = "To generate token", consumes = "application/json")
	public Response generate(@Valid @NotNull JwtUser jwtUser ) {
		JwtGenerator jwtGenerator = new JwtGenerator();
		String token = jwtGenerator.generate(jwtUser);
		
		return Response.status(Status.CREATED).entity(token).build();
	}
}
