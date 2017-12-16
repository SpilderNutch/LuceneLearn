package com.yutian.lucene.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yutian.lucene.dao.HotelDao;
import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.mapper.HotelMapper;

@Service(value="hotelDao")
public class HotelDaoImpl implements HotelDao {
	
	@Resource(name="hotelMapper")
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
