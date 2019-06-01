package com.blog.mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class CommentMapperTest {
	private static final Logger logger = LoggerFactory.getLogger(CommentMapperTest.class);
	
	@Autowired
	private CommentMapper mapper;

	@Test
	public void testCountComments() {
		assertEquals(4, mapper.countComments(142L));
	}

}
