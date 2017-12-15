package com.yutian.logLearn;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yutian.lucene.dao.HotelDao;
import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.mapper.HotelMapper;

public class TestSpringMybatisConnection {

	public static final Logger logger = LoggerFactory.getLogger(TestSpringMybatisConnection.class);
	
	@Test
	public void testSpringMybatisConn() throws Exception{
		logger.info("{} class init begin",this.getClass().getName());
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		//在未添加<bean id = "hotelDao">时，使用HotelMapper进行获取数据库中数据。
		//HotelMapper hotelMapper = (HotelMapper) context.getBean("hotelMapper");
		//Hotel hotel = hotelMapper.selectByPrimaryKey(11232);
		HotelDao hotelDao = (HotelDao) context.getBean("hotelDao");
		Hotel hotel = hotelDao.selectByPrimaryKey(11232);
		System.out.println("hotel Message:"+hotel);
		
		
		logger.info("{} class init end",this.getClass().getName());
		
	}
	
	
}
