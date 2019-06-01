package com.blog.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.vo.PostVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class PostServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceTest.class);
	
	@Autowired
	private PostService service;
	
	@Test
	public void testExist() {
		logger.info("{}", service);
		assertNotNull(service);
	}
	@Test
	public void testGetList() {
		service.getList().forEach(post -> logger.info("{}", post));
	}

	@Test
	public void testGet() {
		logger.info("{}", service.get(1L));
	}

	@Test
	public void testRegister() {
		PostVO post = new PostVO();
		post.setTitle("Service 입력 테스트");
		post.setContent("Service 입력 테스트");
		post.setWriter("Service 입력 테스트");
		logger.info("result: {}", service.register(post));
	}
	
	@Test
	public void testUpdate() {
		PostVO post = service.get(1L);
		if (post == null) {
			return;
		}
		post.setTitle("Service 수정 테스트");
		logger.info("result: " + service.modify(post));
	}
	
	@Test
	public void testRemove() {
		logger.info("result: " + service.remove(142L));
	}
}
