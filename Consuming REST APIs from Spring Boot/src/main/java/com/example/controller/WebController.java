package com.example.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.domain.Activity;

@Controller
public class WebController {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
    @RequestMapping("/")
    public String fun(Model model, RestTemplate restTemplate) {
		String uri = "https://www.boredapi.com/api/activity";
		Activity activity = restTemplate.getForObject(uri, Activity.class);

    	model.addAttribute("activity", activity);
        return "fun";
    }
    
}
