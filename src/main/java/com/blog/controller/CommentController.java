package com.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.service.CommentService;
import com.blog.vo.CommentVO;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private CommentService service;
	
	public CommentController(CommentService service) {
		this.service = service;
	}

	@GetMapping(value="/{pno}", produces = "application/json")
	public List<CommentVO> list(@PathVariable("pno") Long pno) {
		logger.info("list");
		return service.getList(pno);
	}
	
	@PostMapping(value = "/new", consumes = "application/json")
	public void register(@RequestBody CommentVO vo) {
		logger.info("CommentVO: {}", vo);
		service.register(vo); 
	}
	
	@PutMapping(value = "/{cno}")
	public void modify(@RequestBody CommentVO vo, @PathVariable("cno") Long cno) {
		logger.info("modify: {}", vo);
		service.modify(vo); 
	}
	
	@DeleteMapping(value = "/{cno}")
	public void remove(@PathVariable("cno") Long cno, RedirectAttributes rttr) {
		logger.info("remove: {}", cno);
		service.remove(cno);
	}
	
	@PostMapping(value = "/checkPassword", consumes = "application/json",
				 produces = "application/text;charset=utf8")
	public String checkPassword(@RequestBody CommentVO vo) {
		logger.info("CommentVO: {}", vo);
		boolean result = service.checkPassword(vo); 
		logger.info("result: {}", result);
		if(result) {
			return "correct";
		}
		return "different";
	}
}
