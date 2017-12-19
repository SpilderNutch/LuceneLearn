package com.yutian.procon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yutian.lucene.dao.HotelDao;
import com.yutian.lucene.entity.Hotel;

/**
 * 生产者，该生产者直接使用HotelDao
 * @author yuouyang
 *
 */
public class Producer implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(Producer.class);
	
	private volatile boolean isRunning = true;
	private BlockingQueue<Hotel> queue = null; //设置1000的size大小
	private static final int SLEEPTIME = 1000; //进程停止时间
	private static AtomicInteger count = new AtomicInteger(0);
	private static AtomicInteger hotelCount = new AtomicInteger(0);
	private static HotelDao hotelDao;
	static{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String("applicationContext.xml"));
		hotelDao = (HotelDao) context.getBean("hotelDao");
	}
	
	public Producer(BlockingQueue<Hotel> queue){
		this.queue = queue;
	}
	
	public void run() {
		while(isRunning){
			for(;hotelCount.get()<2000; ){
				Hotel hotel = hotelDao.selectByPrimaryKey(hotelCount.incrementAndGet());
				int getCountId = count.incrementAndGet();
				logger.info("getCountId："+getCountId);
				try {
					if (hotel!=null &&!queue.offer(hotel, 2, TimeUnit.SECONDS)) {//加入队列
					    logger.error("add Queue Fail");
					}
				} catch (InterruptedException e) {
					logger.error("message fail:"+e.getMessage());
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	/**
	 * 停止键，停止该进程
	 */
	public void stop(){
		isRunning = false;
	}
	
	
	
	
}
