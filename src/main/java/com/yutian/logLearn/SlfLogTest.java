package com.yutian.logLearn;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学习小结：1，slf4j ,主要的是使用org/slf4j/impl/StaticLoggerBinder对接口进行桥接。
 * 一般而言，slf4j-log4j2-impl.jar 、 common-logging.jar 、JDK logging中存在这个
 * 类，使用LoggerFactory.getLogger形式去获取。
 *  2，当存在多个类似的path路径时，slf4j将选择第一个作为日志的配置。pom.xml中排在第一位。
 * @author yuouyang
 *
 */


public class SlfLogTest {

	@Test
	public void testSlfLog(){
		Logger logger = LoggerFactory.getLogger(SlfLogTest.class.getName());
		System.out.println(logger);
		logger.info("Hello,This is a slfLogTest....");
		
	}
	
}
