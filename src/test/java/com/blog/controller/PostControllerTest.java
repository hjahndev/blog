package com.blog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blog.service.PostService;
import com.blog.vo.PageSettingVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostControllerTest {
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	@Autowired
	private PostService service;
	long pno;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	@WithUserDetails(value="test@mail.com", userDetailsServiceBeanName="customUserDetailsService")
	public void test1Register() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/register")	
				.param("title", "Controller 입력 테스트")
				.param("content", "Controller 입력 테스트")
				.param("writer", "test@mail.com"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/list"));
	}

	@Test
	public void test2Main() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(""))
				.andExpect(status().isOk())
				.andExpect(view().name("post/main"));
	}

	@Test
	public void test3Post() throws Exception {
		pno = service.getListWithSearch(new PageSettingVO()).get(0).getPno();
		mockMvc.perform(MockMvcRequestBuilders.get("/post/"+ String.valueOf(pno)))
				.andExpect(status().isOk())
				.andExpect(view().name("post/post"));
	}
	
	@Test
	@WithMockUser(username="test@mail.com")
	public void test4Modify() throws Exception {
		pno = service.getListWithSearch(new PageSettingVO()).get(0).getPno();
		mockMvc.perform(MockMvcRequestBuilders.post
				("/modify")	
				.param("pno", String.valueOf(pno))
				.param("title", "Controller 수정 테스트")
				.param("content", "Controller 수정 테스트")
				.param("writer", "test@mail.com"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/post/"+pno));
	}
	
	@Test
	@WithUserDetails(value="test@mail.com", userDetailsServiceBeanName="customUserDetailsService")
	public void test5Remove() throws Exception {
		pno = service.getListWithSearch(new PageSettingVO()).get(0).getPno();
		mockMvc.perform(MockMvcRequestBuilders.post
			("/remove")	
			.param("pno", String.valueOf(pno))
			.param("writer", "test@mail.com"))
			.andExpect(status().isFound())
			.andExpect(view().name("redirect:/list"));		
	}
}
