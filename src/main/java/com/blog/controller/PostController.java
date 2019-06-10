package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.service.PostService;
import com.blog.vo.LinkVO;
import com.blog.vo.PageSettingVO;
import com.blog.vo.PagingVO;
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
	public String list(PageSettingVO vo, Model model) {
		logger.info("list pagingvo: {}", vo);
		model.addAttribute("list", service.getListWithPage(vo));
		model.addAttribute("pagination", new PagingVO(vo, service.getTotal()));
		return "post/list";
	}
	
	@GetMapping({"/post/{pno}"})
	public String read(@PathVariable("pno") Long pno, PageSettingVO pageSet, Model model) {
		logger.info("post: {}", pno);
		PostVO vo = service.get(pno);
		if(vo == null) {
			return "error/404";
		}
		model.addAttribute("post", vo);
		model.addAttribute("pageSet", pageSet);
		return "post/post";
	}
	
	@PreAuthorize("principal.username == #vo.writer")
	@PostMapping("/modify")
	public String modify(PostVO vo, RedirectAttributes rttr) {
		logger.info("modify: {}", vo);
		if(service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/list";
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("pno") Long pno,	String writer, RedirectAttributes rttr) {
		logger.info("remove: {}", pno);
		if(service.remove(pno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/list";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public String register(PostVO vo, RedirectAttributes rttr) {
		logger.info("register: {} ", vo.getContent());
		if(service.register(vo)) {
			rttr.addFlashAttribute("result", vo.getPno());		
		}
		return "redirect:/list";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping({"/modify"})
	public String updateForm(@RequestParam("pno") Long pno,@RequestParam("rows") int rows, Model model) {
		logger.info("/updateForm");
		model.addAttribute("post", service.get(pno));
		model.addAttribute("rows", rows);
		return "post/modify";
	}
	
	@ResponseBody
	@GetMapping(value="/getLink/{pno}")
	public LinkVO getLink(@PathVariable("pno") Long pno) {
		logger.info("이전글, 다음글 찾기: {}", pno);
		return service.getLink(pno);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
