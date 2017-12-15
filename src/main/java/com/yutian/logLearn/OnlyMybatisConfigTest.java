package com.yutian.logLearn;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.mapper.HotelMapper;
/**
 * 学习内容：纯使用Mybatis来获取数据库连接查询。
 * @author yuouyang
 *
 */


public class OnlyMybatisConfigTest {

	@Test
	public void testOnlyMybatisConfig() throws IOException{
		
		String resource = "mybatis-config_only.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		System.out.println(sqlSessionFactory);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			HotelMapper hotelMapper = sqlSession.getMapper(HotelMapper.class);
			Hotel hotel = hotelMapper.selectByPrimaryKey(11232);
			System.out.println("hotel Message:"+hotel);
			
		}finally{
			sqlSession.close();
		}
		
		
	}
	
}
