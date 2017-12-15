package com.yutian.lucene.dao.impl;

import com.yutian.lucene.dao.HotelDao;
import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.mapper.HotelMapper;

public class HotelDaoImpl implements HotelDao {
	
	private HotelMapper hotelMapper;
	
	public Hotel selectByPrimaryKey(Integer id) {
		Hotel hotel = null;
		hotel = hotelMapper.selectByPrimaryKey(id);
		System.out.println("hotel Message:"+hotel);
		return hotel;
	}

	public HotelMapper getHotelMapper() {
		return hotelMapper;
	}

	public void setHotelMapper(HotelMapper hotelMapper) {
		this.hotelMapper = hotelMapper;
	}
}
