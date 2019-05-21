package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.service.PostService;
import com.blog.vo.PostVO;

@Controller
@RequestMapping(value = "/")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private PostService service;
	
	public PostController(PostService service) {
		this.service = service;
	}

	@GetMapping(value = "")
	public String main(Model model) {
		model.addAttribute("list", service.getList());
		return "post/main";
	}

	@GetMapping("/list")
	public String list(Model model) {
		logger.info("list");
		model.addAttribute("list", service.getList());
		return "post/list";
	}
	
	@GetMapping({"/post"})
	public String read(@RequestParam("pno") Long pno, Model model) {
		logger.info("post: {}", pno);
		model.addAttribute("post", service.get(pno));
		return "post/post";
	}
	
	@PostMapping("/modify")
	public String modify(PostVO vo, RedirectAttributes rttr) {
		logger.info("modify: {}", vo);
		if(service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("pno") Long pno,	RedirectAttributes rttr) {
		logger.info("remove: {}", pno);
		if(service.remove(pno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/list";
	}
	
	@PostMapping("/register")
	public String register(PostVO vo, RedirectAttributes rttr) {
		logger.info("register: {} ", vo.getContent());
		if(service.register(vo)) {
			rttr.addFlashAttribute("result", vo.getPno());		
		}
		return "redirect:/list";
	}
	
	@GetMapping({"/modify"})
	public String updateForm(@RequestParam("pno") Long pno,@RequestParam("rows") int rows, Model model) {
		logger.info("/updateForm");
		model.addAttribute("post", service.get(pno));
		model.addAttribute("rows", rows);
		return "post/modify";
	}
	
	@GetMapping("/register")
	public String writeForm() {
		return "post/register";
	}
	
	@GetMapping("/about")
	public String about() {
		return "post/about";
	}

	@GetMapping("/login")
	public String login() {
		return "post/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "post/join";
	}
}
