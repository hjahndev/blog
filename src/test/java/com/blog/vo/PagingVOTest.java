package com.blog.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.mapper.PostMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class PagingVOTest {
	@Autowired
	private PostMapper mapper;
	private PagingVO vo;
	private PageSettingVO pageSet;
	int totalPage;
	
	@Before
	public void setup() {
		pageSet = new PageSettingVO();
		vo = new PagingVO(pageSet, mapper.getTotal(pageSet));
		totalPage = (int)Math.ceil((double)mapper.getTotal(pageSet)/5);
	}
	
	@Test
	public void testTotalCount() {
		assertEquals(mapper.getTotal(pageSet), vo.getTotalCount());
	}
	
	@Test
	public void testEndPage() {
		assertEquals(totalPage, vo.getEndPage());
	}
	
	@Test
	public void testTotalPage() {
		assertEquals(totalPage, vo.getTotalPage());
	}

}
