package com.yutian.logLearn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.yutian.lucene.entity.Hotel;

/**
 * 直接用Spring中的JDBCTemplate中获取处理啊。
 * @author yuouyang
 *
 */

public class SpringJdbcTemplateTest {

	@Test
	public void testSpringJdbcTemplate(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql = "SELECT * FROM Hotel where id = '32322'";
		
		@SuppressWarnings("unchecked")
		List<Hotel> hotel = (List<Hotel>) jdbcTemplate.query(sql, new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Hotel hotel = new Hotel();
				hotel.setLgHName(rs.getString("lg_hname"));
				
				return hotel;
			}
		});
		
		System.out.println("hotel:"+hotel);
		
		
	}
	
}
