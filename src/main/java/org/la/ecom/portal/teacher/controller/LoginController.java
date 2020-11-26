package org.la.ecom.portal.teacher.controller;

import org.la.ecom.mysql.api.dto.AuthenticationRequestDTO;
import org.la.ecom.mysql.api.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("")
public class LoginController {

	@GetMapping("/login")
	public ModelAndView loginTest() {

		System.out.println("test login unsecure");

		ModelAndView mav = new ModelAndView("login");

		return mav;
	}
	
	@GetMapping("/registration")
	public ModelAndView registrationTest() {

		System.out.println("test registration unsecure");

		ModelAndView mav = new ModelAndView("registration");

		return mav;
	}
	
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute AuthenticationRequestDTO req) {

		System.out.println("username: " + req.getUsername());

		ModelAndView mav = new ModelAndView("success");

		return mav;
	}
	
	

	@PostMapping("/registration")
	public ModelAndView registration(@ModelAttribute UserDTO user) {

		System.out.println("email: " + user.getEmail());

		ModelAndView mav = new ModelAndView("success");

		return mav;
	}
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		
		System.out.println("secure page");

		ModelAndView mav = new ModelAndView("success");

		return mav;
	}
}
