package com.example.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    
//    @RequestMapping("/securedPage")
//    public String securedPage(Model model, Principal principal) {
//        return "securedPage";
//    }
    
    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        return "home";
    }
    
	@GetMapping("/securedPage")
	public String index(Model model,
						@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
						@AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("username", oauth2User.getName());
		model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
		model.addAttribute("userAttributes", oauth2User.getAttributes());
		return "securedPage";
	}
	
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public String userDetails(Model model, OAuth2AuthenticationToken token) {
    	// The PreAuthorize annotation ensures that this page can only be accessed if the user is authenticated and has the profile scope
    	model.addAttribute("username", token.getPrincipal().getName());
    	model.addAttribute("details", token.getPrincipal().getAttributes());
    	
    	//return new ModelAndView("profile" , model);
    	return "profile";
    }
}