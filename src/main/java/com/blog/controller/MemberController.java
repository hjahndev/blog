package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public void login() {
		logger.info("로그인 화면");
	}
	
	@PostMapping(value = "/logout")
	public void logout() {
		logger.info("로그아웃");
	}
	
	@RequestMapping(value = "/accessError", method = {RequestMethod.GET, RequestMethod.POST})
	public void accessDenied(Authentication auth, Model model) {
		logger.info("access Denied : {}", auth);
		model.addAttribute("msg", "Access Denied");
	}
	
}
