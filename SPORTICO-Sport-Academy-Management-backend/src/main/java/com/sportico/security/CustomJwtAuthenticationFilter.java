package com.sportico.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // a spring bean that can be injected in other spring beans , as dependency
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {
	// will be used for token verification
	// dependency -  JWT utils
	@Autowired
	private JwtUtils utils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// check authorization header from incoming request
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			// => request header contains JWT , so extract it.
			String jwt = authHeader.substring(7);
			Authentication authentication = 
					utils.populateAuthenticationTokenFromJWT(jwt);
			/*
			 * 	save this Authentication object , 
			 * containing - email , user id n granted authorities , 
			 * under spring security context ,  so that subsequent filters will NOT
			 * retry the authentication again (isAuthenticated is already set to true)		
			 */
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("saved auth token in sec ctx");
		}
		filterChain.doFilter(request, response);// to continue with remaining chain of spring sec filters

	}

}
