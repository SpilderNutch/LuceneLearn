package com.yutian.logLearn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 关于Class.getResource(),以及ClassLoader.getResource()相关数据。
 * @author yuouyang
 *
 */


public class OnlyLog4j2Test {

	public static final Logger logger = LogManager.getLogger(OnlyLog4j2Test.class.getName());
	
	
	
	@Test
	public void onlyTestLog4j2(){
		logger.info("This is a only {},{} log4j2 test method", "just","call");
	}
	
	
	@Test
	public void classResourceTest(){
		
		logger.info("class resource without / :"+ OnlyLog4j2Test.class.getResource(""));
		//:/Users/yuouyang/GITIOS/SearchWorkSpace/LuceneLearn/target/classes/com/yutian/logLearn/
		logger.info("class resource with / :"+ OnlyLog4j2Test.class.getResource("/"));
		//:/Users/yuouyang/GITIOS/SearchWorkSpace/LuceneLearn/target/test-classes/
		logger.info("class resource without / :"+ OnlyLog4j2Test.class.getClassLoader().getResource(""));
		
		
		logger.info("class resource with / :"+ OnlyLog4j2Test.class.getClassLoader().getResource("/"));
		
	}
	
	
}
