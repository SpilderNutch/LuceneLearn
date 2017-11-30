package com.yutian.lucene.mapper;

import com.yutian.lucene.entity.Hotel;

public interface HotelMapper  {

    Hotel selectByPrimaryKey(Integer id);
    
}