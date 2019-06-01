package com.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.mapper.MemberMapper;
import com.blog.vo.AuthVO;
import com.blog.vo.MemberVO;
import com.blog.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberMapper mapper;
	@Autowired
	private PasswordEncoder pwencoder;
	
	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public boolean join(MemberVO vo) {
		logger.info("회원가입 service: {}", vo);
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
	public boolean forgotPassword(MemberVO vo) {
		//새 비번 만들어서 암호화하고 이메일 보줌 or 비번변경 화면 링크 보내줌
		return false;
	}

	@Override
	public int checkNickname(String nickname) {
		return mapper.checkNickname(nickname);
	}

	@Override
	public int checkEmail(String email) {
		return mapper.checkEmail(email);
	}

}
