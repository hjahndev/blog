package com.blog.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.blog.vo.LinkVO;
import com.blog.vo.PostVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostMapperTest {
	@Autowired
	private PostMapper mapper;
	
	PostVO post;
	List<PostVO> postList;
	
	@Before
	public void setup() {
		post = new PostVO();
		post.setTitle("Mapper 입력 테스트");
		post.setContent("Mapper 입력 테스트");
		post.setWriter("test@mail.com");

		postList = mapper.getList();
	}
	
	@Test
	public void test1Insert() {
		assertEquals(1, mapper.insert(post));
		assertEquals(1, mapper.insert(post));
		assertEquals(1, mapper.insert(post));
	}
	
	@Test
	public void test2Get() {
		PostVO vo = mapper.get(postList.get(0).getPno());
		assertEquals(post, vo);
	}
	
	@Test
	public void test3Update() {
		post = new PostVO();
		post.setPno(postList.get(0).getPno());
		post.setTitle("Mapper 수정 테스트");
		post.setContent("Mapper 수정 테스트");
		post.setWriter("test@mail.com");
		assertEquals(1, mapper.update(post));
	}

	@Test
	public void test4GetLink() {
		assertNotNull(mapper.getLink(postList.get(1).getPno()));
		LinkVO vo = new LinkVO();
		vo.setPrevPno(postList.get(2).getPno());
		vo.setNextPno(postList.get(0).getPno());
		assertEquals(vo.getPrevPno(), mapper.getLink(postList.get(1).getPno()).getPrevPno());
		assertEquals(vo.getNextPno(), mapper.getLink(postList.get(1).getPno()).getNextPno());
	}
	
	@Test
	public void test5Delete() {
		assertEquals(1, mapper.delete(postList.get(0).getPno()));
		assertEquals(1, mapper.delete(postList.get(1).getPno()));
		assertEquals(1, mapper.delete(postList.get(2).getPno()));
	}

}
