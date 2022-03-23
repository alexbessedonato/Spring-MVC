package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

	@RequestMapping("/greeting")
	public String greeting(Model model) {
		model.addAttribute("service", "greeting");
		return "show";
	}

	@RequestMapping("/best/{what}")
	public String service(@PathVariable String what, Model model) {
		model.addAttribute("service", what);
		return "show";
	}
}
