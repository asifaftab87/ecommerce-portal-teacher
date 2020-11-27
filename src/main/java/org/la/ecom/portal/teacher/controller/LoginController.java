package org.la.ecom.portal.teacher.controller;

import java.net.URISyntaxException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.la.ecom.mysql.api.dto.AuthenticationRequestDTO;
import org.la.ecom.mysql.api.dto.AuthenticationResponseDTO;
import org.la.ecom.mysql.api.dto.UserDTO;
import org.la.ecom.portal.teacher.client.service.ApiServicePortalTeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private ApiServicePortalTeacher apiService;
	
	private final Logger log = LoggerFactory.getLogger(LoginController.class);
			
	@GetMapping("/login")
	public ModelAndView loginTest(@ModelAttribute AuthenticationRequestDTO authRequest) {

		boolean authenticated2 = isAuthenticated();
		log.info("auhten: "+authenticated2);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			boolean authenticated = authentication.isAuthenticated();
			if(authenticated) {
				Object obj = authentication.getPrincipal();
				if(obj!=null && obj instanceof Principal) {
					Principal principal = (Principal)obj;
					if(principal!=null) {
						String name = principal.getName();
						log.info(name);
					}
				}
				log.info(""+authentication.getClass());
				if(!(authentication instanceof AnonymousAuthenticationToken) ) {
					return new ModelAndView("redirect:/welcome", "authRequest", authRequest);
				}
			}
			log.info(authenticated+"");
		}
		
		log.info("test login unsecure");

		ModelAndView mav = new ModelAndView("login");
		mav.addObject(authRequest);
		return mav;
	}
	
	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.
	      isAssignableFrom(authentication.getClass())) {
	        return false;
	    }
	    return authentication.isAuthenticated();
	}
	
	@GetMapping("/registration")
	public ModelAndView registrationTest() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			boolean authenticated = authentication.isAuthenticated();
			log.info(authenticated+"");
		}
		log.info("test registration unsecure");

		ModelAndView mav = new ModelAndView("registration");

		return mav;
	}
	
	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest req,@ModelAttribute AuthenticationRequestDTO authRequest) {

		log.info("username: " + authRequest.getUsername());

		AuthenticationResponseDTO authenticationResponseDTO = null;
		try {
			authenticationResponseDTO = 
				apiService.securityClient().postForObject("/authenticate", authRequest, AuthenticationResponseDTO.class);
			
			String jwt = authenticationResponseDTO.getJwt();
			req.getSession().setAttribute("jwt", jwt);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/test", "authRequest", authRequest);
	}
	
	@GetMapping("/welcome")
	public ModelAndView welcome(@ModelAttribute AuthenticationRequestDTO authRequest) {
		
		log.info("welcome");
		ModelAndView mav = new ModelAndView("welcome");

		return mav;
	}
	
	@GetMapping("/test")
	public ModelAndView test(@ModelAttribute AuthenticationRequestDTO authRequest) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			boolean authenticated = authentication.isAuthenticated();
			if(authenticated) {
				log.info(""+authentication.getClass());
				if(!(authentication instanceof AnonymousAuthenticationToken) ) {
					return new ModelAndView("redirect:/welcome", "authRequest", authRequest);
				}
			}
			log.info(authenticated+"");
		}
		
		log.info("test--------------------------: ");
		ModelAndView mav = new ModelAndView("registration");

		return mav;
	}
	
	@GetMapping("/test2")
	public ModelAndView test2(@ModelAttribute AuthenticationRequestDTO authRequest) {
		
		log.info("test--------------------------: ");
		ModelAndView mav = new ModelAndView("registration");

		return mav;
	}

	@PostMapping("/registration")
	public ModelAndView registration(@ModelAttribute UserDTO user) {

		log.info("email: " + user.getEmail());

		ModelAndView mav = new ModelAndView("success");

		return mav;
	}
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		
		log.info("secure page");

		ModelAndView mav = new ModelAndView("success");

		return mav;
	}
}
