package com.amazonaws.samples.tlc.amazontlcwebapp.controller;

import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String home(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			model.addAttribute("name", authentication.getName());
			model.addAttribute("principal", authentication.getPrincipal());
			model.addAttribute("authorities", authentication.getAuthorities().stream().map(a -> a.getAuthority())
					.collect(Collectors.joining(",")));
		}

		model.addAttribute("message", "AWS Cognito with Spring Security");

		return "index";
	}
}
