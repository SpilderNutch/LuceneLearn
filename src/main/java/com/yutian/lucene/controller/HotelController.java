package com.yutian.lucene.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.service.HotelService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	Logger logger = LoggerFactory.getLogger(HotelController.class);

	@Resource(name="hotelService")
	private HotelService hotelService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response,String id){
		if(id==null || "".equals(id)){
			id = "23679";
		}
		Hotel hotel = hotelService.selectByPrimaryKey(Integer.parseInt(id));
		
		logger.info("hotel message:"+hotel.toString());
		
		return "/index.jsp";
	}

	/** getter and setter methods**/
	public HotelService getHotelService() {
		return hotelService;
	}

	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	
}
