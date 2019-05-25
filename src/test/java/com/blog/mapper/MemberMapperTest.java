package com.blog.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberMapperTest.class);
	
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void test() {
		logger.warn("{}", mapper);
		logger.warn("{}", mapper.checkUser("admin90"));
	}

}
