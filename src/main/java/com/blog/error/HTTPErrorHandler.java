package com.blog.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/error")
public class HTTPErrorHandler {

    @GetMapping(value="/404")
    public String error(HttpServletRequest request){
    	return "/error/404";
    }
}
