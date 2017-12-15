package com.yutian.lucene.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yutian.lucene.dao.HotelDao;
import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.mapper.HotelMapper;

public class HotelDaoImpl{ // implements HotelDao {
	/**
	private SqlSessionFactory sqlSessionFactory;
	
	public Hotel selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Hotel hotel = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			HotelMapper hotelMapper = sqlSession.getMapper(HotelMapper.class);
			hotel = hotelMapper.selectByPrimaryKey(11232);
			System.out.println("hotel Message:"+hotel);
			
		}finally{
			sqlSession.close();
		}
		return hotel;
	}


	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}


	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
*/
}
