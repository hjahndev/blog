package com.blog.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		         "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	
	@Autowired
	private MemberService service;
	private MemberVO vo;
	
	@Autowired
	private PasswordEncoder pwencoder;

	public void setPwencoder(PasswordEncoder pwencoder) {
		this.pwencoder = pwencoder;
	}
	
	@Before
	public void setup() {
		vo = new MemberVO();
		vo.setEmail("test2@mail.com");
		vo.setNickname("test2");
		vo.setPassword("123");
		vo.addAuth("ROLE_ADMIN");
	}
	
	@Test
	public void testExist() {
		logger.info("{}", service);
		assertNotNull(service);
	}
	
	@Test
	public void testJoin() {
		logger.info("testJoin: {}", service.join(vo));
	}
	
	@Test
	public void testPasswordEncoding() {
		vo.encodePassword(new BCryptPasswordEncoder());
		logger.info("password: {}, encodedPassword: {}", "123", vo.getPassword());
		assertTrue(pwencoder.matches("123", vo.getPassword()));
	}

}
