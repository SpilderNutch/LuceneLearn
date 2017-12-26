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
		BlockingQueue<Hotel> queue = new LinkedBlockingQueue<Hotel>(100);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);
		
		logger.info("==========Runnable init begin to Run=================");
		
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(producer);
		service.execute(consumer);
		service.execute(consumer2);
		
		Thread.sleep(4*60*60*1000);
		
		logger.info("Thread.sleep over!!");
		
		if(!producer.isRunning()){
			consumer.stop();
			consumer2.stop();
		}
		
		try{
			if(producer.isRunning()){//暂未结束
				producer.stop();//停止
				logger.info("index record:"+producer.getHotelCount().get());
				consumer.stop();
				consumer2.stop();
			}
		}catch(Exception e){
			logger.error("stop 、shutdown error!");
		}
		
		logger.info("==========Runnable init end to Run=================");
		
		service.shutdown();
		logger.info("==========service.shutdown()=================");
		
	}
	
	
}
