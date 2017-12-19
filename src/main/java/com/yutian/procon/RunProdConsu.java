package com.yutian.procon;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutian.lucene.entity.Hotel;

/**
 * 根据生产者，执行者来运行该方法
 * @author yuouyang
 *
 */
public class RunProdConsu {

	public static Logger logger = LoggerFactory.getLogger(RunProdConsu.class);
	
	public static void main(String[] args) throws InterruptedException, IOException {
		BlockingQueue<Hotel> queue = new LinkedBlockingQueue<Hotel>(40);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		logger.info("==========Runnable init begin to Run=================");
		
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(producer);
		service.execute(consumer);
		
		Thread.sleep(10*1000);
		producer.stop();
		consumer.stop();
		
		logger.info("==========Runnable init end to Run=================");
		
		service.shutdown();
		
	}
	
	
}
