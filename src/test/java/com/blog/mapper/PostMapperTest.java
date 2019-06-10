package com.blog.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.vo.LinkVO;
import com.blog.vo.PostVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class PostMapperTest {
	private static final Logger logger = LoggerFactory.getLogger(PostMapperTest.class);

	@Autowired
	private PostMapper mapper;

	@Test
	public void testGetList() {
		mapper.getList().forEach(post -> logger.info("{}", post));
	}
	
	@Test
	public void testGet() {
		PostVO vo = mapper.get(1L);
		logger.info("{}", vo);
	}
	
	@Test
	public void testInsert() {
		PostVO post = new PostVO();
		post.setTitle("Mapper 입력 테스트");
		post.setContent("Mapper 입력 테스트");
		post.setWriter("Mapper 입력 테스트");
		logger.info("result: {}", mapper.insert(post));
	}
	@Test
	public void testDelete() {
		logger.info("result: {}", mapper.delete(6L));
	}
	
	@Test
	public void testUpdate() {
		PostVO post = new PostVO();
		post.setPno(5L);
		post.setTitle("Mapper 수정 테스트");
		post.setContent("Mapper 수정 테스트");
		post.setWriter("Mapper 수정 테스트");
		logger.info("result: {}", mapper.update(post));
	}
	
	@Test
	public void testGetLink() {
		assertNotNull(mapper.getLink(121L));
		LinkVO vo = new LinkVO();
		vo.setPrevPno(23L);
		vo.setNextPno(95L);
		assertEquals(vo.getPrevPno(), mapper.getLink(94L).getPrevPno());
		assertEquals(vo.getNextPno(), mapper.getLink(94L).getNextPno());
	}

}
