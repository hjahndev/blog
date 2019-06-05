package com.blog.error;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/error")
public class HTTPErrorHandler {
	private static final Logger logger = LoggerFactory.getLogger(HTTPErrorHandler.class);
    
	@GetMapping(value="/404")
    public String error404(HttpServletRequest request, Model model) {
    	logger.info("error 404");
    	model.addAttribute("code", "404");
    	model.addAttribute("msg", "페이지를 찾을 수 없습니다.");
    	model.addAttribute("explain", "죄송합니다. 주소가 변경 혹은 삭제되어 찾을 수 없는 페이지 입니다.");
    	return "/error/error";
    }
    
    @GetMapping(value="/500")
    public String error500(HttpServletRequest request, Model model) {
    	logger.info("error 500");
    	model.addAttribute("code", "500");
    	model.addAttribute("msg", "일시적 오류가 발생했습니다.");
    	model.addAttribute("explain", "불편을 끼쳐드려 죄송합니다. 잠시 후 다시 이용해 주시길 바랍니다.");
    	return "/error/error";
    }
    
    @GetMapping(value="/invalidToken")
    public String invalidToken(HttpServletRequest request, Model model) {
    	logger.info("error invalidToken");
    	model.addAttribute("msg", "사용하신 비밀번호 재설정 페이지는 다시 사용하실 수 없습니다.");
    	model.addAttribute("explain", "비밀번호 재설정을 다시 신청해 주시기바랍니다.");
    	return "/error/error";
    }
}
