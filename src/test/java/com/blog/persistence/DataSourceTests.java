package com.blog.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

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
public class DataSourceTests {
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		
		try (Connection con = dataSource.getConnection()) {
			assertNotNull(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
