package com.blog.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.mapper.MemberMapper;
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
	public boolean forgotPassword(String email) {
		logger.info("비밀번호 찾기: {}", email);
		TokenVO vo = new TokenVO(email);
		int result = mapper.addToken(vo);

		final MimeMessagePreparator preparator = new MimeMessagePreparator() { 
			@Override 
			public void prepare(MimeMessage mimeMessage) throws Exception { 
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				helper.setFrom("hjahn.dev@gmail.com"); 
				helper.setTo(email); 
				helper.setSubject("[blog] 비밀번호 재설정 메일입니다."); 
				helper.setText(new StringBuffer().append("<p>아래 링크를 눌러 비밀번호를 재설정하세요.</p>")
								.append("<a href='http://localhost:8080/member/resetPassword?email=")
								.append(email)
								.append("&token=")
								.append(vo.getToken())
								.append("'>비밀번호 재설정</a>")
								.toString(), true); 
			} 
		}; 
		mailSender.send(preparator); 

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

}
