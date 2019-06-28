package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.service.MemberService;
import com.blog.vo.MemberVO;
import com.blog.vo.TokenVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService service;
	
	public MemberController(MemberService service) {
		this.service = service;
	}
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public void login() {
		logger.info("로그인 화면");
	}
	
	@ResponseBody
	@RequestMapping(value = "/logoutSuccess", method = {RequestMethod.GET, RequestMethod.POST})
	public String logoutSuccess() {
		logger.info("로그아웃");
		return "/member/login";
	}

	@GetMapping(value = "/join")
	public void joinForm() {
		logger.info("회원가입 화면");
	}
	
	@GetMapping(value = "/forgotPassword")
	public String forgotPasswordForm() {
		logger.info("비밀번호 찾기 화면");
		return "/member/forgot-password";
	}
	
	@PostMapping(value = "/join")
	public String join(MemberVO vo, Model model) {
		logger.info("회원가입: {}", vo);
		service.join(vo);
		model.addAttribute("nickname", vo.getNickname());
		return "/member/joinSuccess";
	}
	
	@ResponseBody
	@GetMapping(value="/checkNickname/{nickname}", 
				produces = "application/text;charset=utf8")
	public String checkNickname(@PathVariable("nickname") String nickname) {
		logger.info("닉네임 중복체크: {}", nickname);
		if(service.checkNickname(nickname)) { 
			return "duplicate";
		}
		return "original";
	}
	
	@ResponseBody
	@GetMapping(value="/checkEmail", consumes = "application/json",
				produces = "application/text;charset=utf8")
	public String checkEmail(@RequestParam("email") String email) {
		logger.info("이메일 중복체크: {}", email);
		if(service.checkEmail(email)) { 
			return "duplicate";
		}
		return "original";
	}
	
	@ResponseBody
	@GetMapping(value = "/passwordEmail", consumes = "application/json",
				produces = "application/text;charset=utf8")
	public String forgotPassword(@RequestParam("email") String email) {
		logger.info("비밀번호 찾기 {}", email);
		if(!service.checkEmail(email)) {
			logger.info("등록되지 않은 이메일");
			return "등록되지 않은 이메일입니다.";
		}	
		if(service.sendPasswordResetPage(email)) {
			return "메일을 발송했습니다.";
		}
		return "메일을 발송할 수 없습니다.";
	}
	
	@GetMapping(value = "/resetPassword")
	public String resetPasswordForm(TokenVO vo, Model model) {
		logger.info("비밀번호 재설정 화면: {}", vo);
		if(!service.checkToken(vo)) {
			return "/error/invalidToken";
		}
		model.addAttribute("token", vo.getToken());
		model.addAttribute("email", vo.getEmail());
		return "/member/reset-password";
	}
	
	@PostMapping(value = "/resetPassword")
	public String resetPassword(MemberVO vo, TokenVO token, Model model) {
		logger.info("비밀번호 재설정: {} {}", vo, token);
		service.resetPassword(vo, token);
		return "/member/resetSuccess";
	}
	
	@RequestMapping(value = "/accessError", method = {RequestMethod.GET, RequestMethod.POST})
	public String accessDenied(Authentication auth, Model model) {
		logger.info("access Denied : {}", auth);
		model.addAttribute("code", "접근 불가능");
		model.addAttribute("msg", "접근 불가능한 페이지입니다.");
		model.addAttribute("explain", "요청하신 페이지를 실행할 권한이 없습니다.");
		return "/error/error";
	}
	
}
