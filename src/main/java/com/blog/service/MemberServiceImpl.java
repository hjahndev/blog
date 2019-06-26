package com.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.mapper.MemberMapper;
import com.blog.util.Mail;
import com.blog.vo.AuthVO;
import com.blog.vo.MemberVO;
import com.blog.vo.TokenVO;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
	private PasswordEncoder pwencoder;
	@Autowired
	private JavaMailSender mailSender;
	
	private MemberMapper mapper;
	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public boolean join(MemberVO vo) {
		logger.info("회원가입: {}", vo);
		vo.encodePassword(pwencoder);
		vo.addAuth("ROLE_MEMBER");
		mapper.join(vo);
		int result = 0;
		for (AuthVO authVO : vo.getAuthList()) {
			result += mapper.addAuth(authVO);
		} 
		return result > 0 ? true : false;
	}

	@Override
	public boolean sendPasswordResetPage(String email) {
		logger.info("비밀번호 찾기: {}", email);
		TokenVO vo = new TokenVO(email);
		int result = mapper.addToken(vo);
		Mail mail = new Mail(mailSender);
		mail.sendPasswordResetPage(vo);
		return result == 1;
	}

	@Override
	public boolean checkNickname(String nickname) {
		return mapper.checkNickname(nickname) == 1;
	}

	@Override
	public boolean checkEmail(String email) {
		return mapper.checkEmail(email) == 1;
	}

	@Override
	public boolean checkToken(TokenVO vo) {
		logger.info("비밀번호 재설정 토큰 확인: {}", vo);
		return mapper.checkToken(vo) == 1;
	}

	@Transactional
	@Override
	public boolean resetPassword(MemberVO vo, TokenVO token) {
		logger.info("비밀번호 재설정: {} {}", vo, token);
		mapper.deleteToken(token);
		vo.encodePassword(pwencoder);
		return mapper.resetPassword(vo) == 1;
	}

	@Transactional
	@Override
	public boolean cancelMembership(MemberVO vo) {
		mapper.deleteAuth(vo);
		return mapper.cancelMembership(vo) == 1;
	}

}
