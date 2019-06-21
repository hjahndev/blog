package com.blog.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.vo.CommentVO;
import com.blog.vo.PageSettingVO;
import com.blog.vo.PostVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentMapperTest {
	@Autowired
	private CommentMapper mapper;
	@Autowired
	private PostMapper postMapper;
	
	long pno;
	@Before
	public void setup() {
		PostVO post = new PostVO();
		post.setTitle("Mapper 입력 테스트");
		post.setContent("Mapper 입력 테스트");
		post.setWriter("test@mail.com");
		postMapper.insert(post);
		pno = postMapper.getListWithSearch(new PageSettingVO()).get(0).getPno();
	}
	
	@Test
	public void testCountComments() {
		assertEquals(0, mapper.countComments(pno));
		postMapper.delete(pno);
	}

}
