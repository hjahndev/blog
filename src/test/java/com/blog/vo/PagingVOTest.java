package com.blog.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.controller.PostController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class PagingVOTest {
	private PagingVO vo;
	
	@Before
	public void setup() {
		vo = new PagingVO(new PageSettingVO(), 13);
	}
	
	@Test
	public void testTotalCount() {
		assertEquals(13, vo.getTotalCount());
	}
	
	@Test
	public void testEndPage() {
		assertEquals(3, vo.getEndPage());
	}
	
	@Test
	public void testTotalPage() {
		assertEquals(3, vo.getTotalPage());
	}

}
