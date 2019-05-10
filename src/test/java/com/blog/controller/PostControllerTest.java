package com.blog.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blog.service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class PostControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(PostControllerTest.class);
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testMain() throws Exception {
		logger.info("{}",
				mockMvc.perform(MockMvcRequestBuilders.get(""))	
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}

	@Test
	public void testPost() throws Exception {
		logger.info("{}",
				mockMvc.perform(MockMvcRequestBuilders
				.get("/post")	
				.param("pno", "1"))
				.andReturn()
				.getModelAndView()
				.getModelMap());		
	}
	
	@Test
	public void testRegister() throws Exception {
		String resultPage =	mockMvc.perform(MockMvcRequestBuilders.post
				("/register")	
				.param("title", "Controller 입력 테스트")
				.param("content", "Controller 입력 테스트")
				.param("writer", "Controller 입력 테스트")
			).andReturn().getModelAndView().getViewName();
		logger.info(resultPage);
	}
	
	@Test
	public void testModify() throws Exception {
		String resultPage =	mockMvc.perform(MockMvcRequestBuilders.post
				("/modify")	
				.param("pno", "10")
				.param("title", "Controller 수정 테스트")
				.param("content", "Controller 수정 테스트")
				.param("writer", "Controller 수정 테스트")
			).andReturn().getModelAndView().getViewName();
		logger.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception {
		String resultPage =	mockMvc.perform(MockMvcRequestBuilders.post
			("/remove")	
			.param("pno", "5")
			).andReturn().getModelAndView().getViewName();
		logger.info(resultPage);	
	}
}
