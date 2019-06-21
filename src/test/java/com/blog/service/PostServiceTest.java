package com.blog.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.vo.PostVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceTest.class);
	
	@Autowired
	private PostService service;
	
	PostVO post;
	List<PostVO> postList;
	
	@Before
	public void setup() {
		post = new PostVO();
		post.setTitle("Service 입력 테스트");
		post.setContent("Service 입력 테스트");
		post.setWriter("test@mail.com");
		
		postList = service.getList();
	}
	
	@Test
	public void test1Exist() {
		assertNotNull(service);
	}
	
	@Test
	public void test2Register() {
		logger.info("result: {}", service.register(post));
		assertEquals(post, service.get(postList.get(0).getPno()));
	}
	
	@Test
	public void test4Update() {
		PostVO post = service.get(postList.get(0).getPno());
		post.setTitle("Service 수정 테스트");
		post.setContent("Service 수정 테스트");
		assertEquals(true, service.modify(post));
	}
	
	@Test
	public void test5Remove() {
		assertEquals(true,  service.remove(postList.get(0).getPno()));
	}
}
