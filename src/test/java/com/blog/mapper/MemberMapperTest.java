package com.blog.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.vo.AuthVO;
import com.blog.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper mapper;
	
	private MemberVO vo;
	
	@Before
	public void setup() {
		vo = new MemberVO();
		vo.setEmail("test@mail.com");
		vo.setNickname("test");
		vo.setPassword("1234");
		vo.encodePassword(new BCryptPasswordEncoder());
		vo.addAuth("ROLE_ADMIN");	
	}
	
	@Test
	public void test1CheckUser() {
		mapper.deleteAuth(vo);
		mapper.cancelMembership(vo);
		assertNull(mapper.checkUser("test@mail.com"));
	}
	
	@Test
	public void test2Join() {
		assertEquals(1, mapper.join(vo));
	}
	
	@Test
	public void test3Auth() {
		for (AuthVO auth : vo.getAuthList()) {
			assertEquals(1, mapper.addAuth(auth));
		}
	}
}
