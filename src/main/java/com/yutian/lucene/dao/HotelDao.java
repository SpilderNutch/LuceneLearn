package com.yutian.lucene.dao;

import com.yutian.lucene.entity.Hotel;

public interface HotelDao {

    Hotel selectByPrimaryKey(Integer id);
    
}