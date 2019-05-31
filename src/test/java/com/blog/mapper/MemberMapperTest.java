package com.blog.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.vo.AuthVO;
import com.blog.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberMapperTest.class);
	
	@Autowired
	private MemberMapper mapper;
	
	private MemberVO vo;
	
	@Before
	public void setup() {
		vo = new MemberVO();
		vo.setEmail("test@mail.com");
		vo.setNickname("test");
		vo.setPassword("123");
		vo.encodePassword(new BCryptPasswordEncoder());
		vo.addAuth("ROLE_ADMIN");	
	}
	
	@Test
	public void testCheckUser() {
		logger.warn("{}", mapper.checkUser("test@mail.com"));
	}
	
	@Test
	public void testJoin() {
		assertEquals(1, mapper.join(vo));
	}
	
	@Test
	public void testAuth() {
		for (AuthVO auth : vo.getAuthList()) {
			assertEquals(1, mapper.addAuth(auth));
		}
	}
}
