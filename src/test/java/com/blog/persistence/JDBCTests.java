package com.blog.persistence;

import static org.junit.Assert.fail;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCTests {
	private static final Logger logger = LoggerFactory.getLogger(JDBCTests.class);
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection con =
			DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl11",
					"SCOTT",
					"tiger")) {
			logger.info("{}", con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
