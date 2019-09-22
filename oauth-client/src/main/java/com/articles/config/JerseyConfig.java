package com.articles.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.articles.resource.RootResource;

@Component
@ApplicationPath("/cms")
public class JerseyConfig extends ResourceConfig {

    
    @PostConstruct
	  public void init() {
    	this.registerEndpoints();
	    this.SwaggerConfig();
	    
	  }
	private void SwaggerConfig() {
		
	    BeanConfig swaggerConfigBean = new BeanConfig();
	    swaggerConfigBean.setConfigId(SwaggerContextService.CONFIG_ID_DEFAULT);
	    swaggerConfigBean.setTitle("KnowledgeBase Articles ");
	    swaggerConfigBean.setVersion("v1");
	    swaggerConfigBean.setContact("Naveen Purmani");
	    swaggerConfigBean.setSchemes(new String[] {"http"});
	    swaggerConfigBean.setBasePath("/cms");
	    swaggerConfigBean.setResourcePackage("com.articles");
	    swaggerConfigBean.setPrettyPrint(true);
	    swaggerConfigBean.setScan(true);
	  }
   
	private void registerEndpoints() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
		this.register(SecurityFilter.class);
        this.register(RootResource.class);  	
        }

}	
