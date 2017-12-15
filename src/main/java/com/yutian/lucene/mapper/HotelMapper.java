package com.yutian.lucene.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yutian.lucene.entity.Hotel;

public interface HotelMapper  {

	/**
	 * 该方法使用了annotation方法，能够根据mybatis获取到相应的数据。
	 * 貌似数据库字段与实体字段应该相同才能匹配，我现在需要使用mapper.xml
	 * 文件，从而实现数据库表中不同的字段与实体之间的匹配。
	 * @param id
	 * @return
	 
	@Select("SELECT * FROM Hotel WHERE id = #{id}")
    Hotel selectByPrimaryKey(@Param("id")Integer id);
    */
    
	Hotel selectByPrimaryKey(Integer id);

}