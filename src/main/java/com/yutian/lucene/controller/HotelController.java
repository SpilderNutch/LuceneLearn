package com.yutian.lucene.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.mapper.HotelMapper;

public class HotelController {

	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
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
