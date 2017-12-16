package com.yutian.lucene.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yutian.lucene.dao.HotelDao;
import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.service.HotelService;

@Service(value="hotelService")
public class HotelServiceImpl implements HotelService{

	@Resource(name="hotelDao")
	private HotelDao hotelDao;
	
	public Hotel selectByPrimaryKey(Integer id) {
		return hotelDao.selectByPrimaryKey(id);
	}

	public HotelDao getHotelDao() {
		return hotelDao;
	}

	public void setHotelDao(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

}
