package com.example.spring.oauth2.misoca.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String index(Model model, @AuthenticationPrincipal OAuth2User user) {

		log.debug("user :{}", user);
		model.addAttribute("user", user);

		return "index";
	}
}
