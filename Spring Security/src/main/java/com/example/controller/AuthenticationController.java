package com.example.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.MyUser;
import com.example.repository.MyUserRepository;

@Controller
public class AuthenticationController {

	@Autowired
	MyUserRepository repo;

	@RequestMapping(value = "/login-form", method = RequestMethod.GET)
	public String loginForm() {
		return "security/login";
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public String invalidLogin(Model model) {
		model.addAttribute("error", true);
		return "security/login";
	}

	/**
	 * Successful login is a redirect based on the role of the user
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/success-login", method = RequestMethod.GET)
	public String successLogin(Principal principal) {
		MyUser user = repo.findByName(principal.getName());
		if (user.getRoles().isEmpty()) {
			return "security/denied";
		}
		return "redirect:/greeting";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String error() {
		return "security/denied";
	}
}