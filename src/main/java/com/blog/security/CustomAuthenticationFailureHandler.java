package com.blog.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		logger.warn("login fail");
		 if(exception instanceof BadCredentialsException
	        ||exception instanceof InternalAuthenticationServiceException) {
			 request.setAttribute("errormsg", "아이디나 비밀번호가 맞지 않습니다.");
	     }
		 request.getRequestDispatcher("/member/login?error")
		 .forward(request, response);
		 
	}
}
