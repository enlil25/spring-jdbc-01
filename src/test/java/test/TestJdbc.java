package test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:datasource-test.xml"})
public class TestJdbc {
	
	private static Log logger = LogFactory.getLog("TestJdbc");
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void test() {
		logger.info("Inicio del Test jdbc");
		int personas = jdbcTemplate.queryForObject("select count(*) from persona", Integer.class);
		logger.info("Numero de personas:"+personas);
		assertEquals(3, personas);
		logger.info("Fin del test jdbc");
	}

}
