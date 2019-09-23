package com.articles.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

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
	
	@GET
	@PermitAll
	@Path("/unsecured")
	@ApiOperation(value = "To call unsecured path", produces = "application/json")
	public Response unsecurePath( ) {
		return Response.status(Status.OK).entity("GET successfull for unsecure path").build();
	}
}
