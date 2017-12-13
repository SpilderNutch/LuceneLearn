package com.yutian.logLearn;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
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
		//SqlSessionFactory sessionFactory =  (SqlSessionFactory) context.getBean("sqlSessionFactory");
		//SqlSessionFactory sessionFactory = sessionFacotyBean.getObject();
		//SqlSession sqlSession = sessionFactory.openSession();
//		try{
//			HotelMapper hotelMapper = sqlSession.getMapper(HotelMapper.class);
//			Hotel hotel = hotelMapper.selectByPrimaryKey(11232);
//			System.out.println("hotel Message:"+hotel);
//			
//		}finally{
//			sqlSession.close();
//		}
		
		HotelDao hotelDao = (HotelDao) context.getBean("userDao");
		Hotel hotel = hotelDao.selectByPrimaryKey(11232);
		System.out.println("hotel Message:"+hotel);
		
		
		logger.info("{} class init end",this.getClass().getName());
		
	}
	
	
}
