package org.la.ecom.portal.teacher.jwt;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.la.ecom.portal.teacher.rest.template.interceptor.RestTemplateInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private RestTemplateInterceptor restTemplateInterceptor;
	
	private final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
			
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String jwt = null;
		
		log.info("jwt: "+jwt);
		
		//getting jwt from session
		Object obj = request.getSession().getAttribute("jwt");
		
		if(obj!=null) {
			//casting jwt value from object to string
			jwt = (String)obj;
		}
		
		if(jwt==null) {
			String authorizationHeader = request.getHeader("Authorization");
			if(authorizationHeader!=null) {
				jwt = authorizationHeader.substring(7);
			}
		}
		
		String username = null;
		
		//get path
		String uri = request.getRequestURI();
		
		if(uri!=null) {
			
			String[] pathArray = uri.split("/");
			
			if(pathArray.length>0) {
				
				String finalPath = pathArray[pathArray.length-1];
				
				//checking if the path is login or registration then allow 
				//to pass the security
				if(finalPath.equals("login") || finalPath.equals("registration")) {
					filterChain.doFilter(request, response);
					return;
				}
			}
		}
		
		if(jwt==null) {
			throw new AccessDeniedException("Forbidden");
		}
		
		if(jwt!=null) {
			
			username = jwtUtil.extractUsername(jwt);
			restTemplateInterceptor.setJwt(jwt);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			if(jwtUtil.validateToken(jwt)) {
				
				Set<SimpleGrantedAuthority> ssga = new HashSet<>();
				
				Claims extractAllClaims = jwtUtil.extractAllClaims(jwt);
				
				//checking authorities/role are there or not ?
				boolean containsKey = extractAllClaims.containsKey("authorities");
				
				if(containsKey) {
					
					//extracting all roles/authorities
					List<Map<String, String>> authorities = (List<Map<String, String>>)extractAllClaims.get("authorities");
					
					//extracting roles and adding into set SimpleGrantedAuthority
					authorities.stream().forEach(authority ->
					{
						String role = authority.get("authority");
						
						SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
						ssga.add(sga);
					});
				}
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						username, null, ssga);
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//setting authentication holder
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				filterChain.doFilter(request, response);
			}
		}
	
	}

}
