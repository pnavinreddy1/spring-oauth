package com.articles.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * This filter verify the access permissions for a user based on
 * user name and password provided in request
 * */
@Provider
public class SecurityFilter implements javax.ws.rs.container.ContainerRequestFilter
{
	private static final String ROLE = "role";
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED).build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN).build();
    
    @Context
    private ResourceInfo resourceInfo;
      
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
    	
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
            if(requestContext.getUriInfo().getRequestUri().toString().contains("/swagger")) {
            	return ;
            }  
          //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
          
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
            
			final String role = (headers.get(ROLE) == null || headers
					.get(ROLE).isEmpty()) ? null : headers.get(ROLE)
					.get(0);
            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
			if(role == null || role.isEmpty())
			{
				requestContext.abortWith(ACCESS_DENIED);
				return;
			}
              
            //Verify role access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                  
                //Is role valid?
                if( ! isValidRole(role, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }
    private boolean isValidRole(final String role, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
          
        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
//        String userRole = "ADMIN";
          
        //Step 2. Verify user role
        if(rolesSet.contains(role))
        {
            isAllowed = true;
        }
        return isAllowed;
    }
}