package com.yutian.procon;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.yutian.lucene.entity.Hotel;
import com.yutian.util.DateTool;

/**
 * 消费者，消费数据,同样也是进程，用于消费生产者生产的数据
 * @author yuouyang
 * @date 2017/12/18
 */
public class Consumer implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(Consumer.class);
	private volatile boolean isRunning = true;
	private BlockingQueue<Hotel> queue;//阻塞队列
	private static IndexWriter indexWriter;
	static{
		Analyzer analyzer = new StandardAnalyzer();
		FSDirectory fsDirectory = null;
		try {
			fsDirectory = FSDirectory.open(Paths.get("/Users/yuouyang/javadev/hotelindex"));
		} catch (IOException e) {
			logger.error("e stack message:"+e.getMessage());
			logger.error("Try to open directory {/Users/yuouyang/javadev/hotelIndex} fail");
		}
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		try {
			indexWriter = new IndexWriter(fsDirectory, config);
		} catch (IOException e) {
			logger.error("e stack message:"+e.getMessage());
			logger.error("init IndexWriter fail.");
		}
	}
	
	
	public Consumer(BlockingQueue<Hotel> queue){
		this.queue = queue;
	}
	
	public void run() {
		while(isRunning){
			try {
				Hotel hotel = queue.take();
				logger.info("Consumer take..");
				Document doc = hotolTranslateToDoc(hotel);//将hotel转化为Doc文件
				try {
					indexWriter.addDocument(doc);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			} catch (InterruptedException e) {
				logger.error("queue take fail.Reason:"+e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}

	//将Hotel数据转化为Doc，可以索引
	private Document hotolTranslateToDoc(Hotel hotel) {
		Document doc = new Document();
		doc.add(new Field("id", hotel.getId().toString(), TextField.TYPE_STORED));
		doc.add(new Field("lkName", nullToEmpty(hotel.getLkName()), TextField.TYPE_STORED));
		doc.add(new Field("lkSex", nullToEmpty(hotel.getLkSex()), TextField.TYPE_STORED));
		doc.add(new Field("lkIdCode", nullToEmpty(hotel.getLkIdCode()), TextField.TYPE_STORED));
		doc.add(new Field("lkAddress", nullToEmpty(hotel.getLkAddress()), TextField.TYPE_STORED));
		doc.add(new Field("lgHName", nullToEmpty(hotel.getLgHName()), TextField.TYPE_STORED));
		doc.add(new Field("lgHAddress", nullToEmpty(hotel.getLgHAddress()), TextField.TYPE_STORED));
		doc.add(new Field("lkNoroom", nullToEmpty(hotel.getLkNoroom()), TextField.TYPE_STORED));
		doc.add(new Field("lkLtime", dateToStr(hotel.getLkLtime()), TextField.TYPE_STORED));
		doc.add(new Field("lkEtime", dateToStr(hotel.getLkEtime()), TextField.TYPE_STORED));
		return doc;
	}

	public void stop() throws IOException{
		isRunning = false;
		indexWriter.close();
		logger.info("indexWriter is closed !");
	}
	
	//判断空值与非空值,若为null,转化为""
	public static String nullToEmpty(String orgin){
		if(Strings.isNullOrEmpty(orgin)){
			return "";
		}
		return orgin;
	}
	
	//时间转化
	public static String dateToStr(Date date){
		String result = "";
		if(date == null){
			return result;
		}
		try{
			result = DateTool.dateToString(date, DateTool.dateTimeFormat);
		}catch(Exception e){
			logger.error("DateTool.dateToString error :"+date);
			result = "";
		}
		return result;
	}
}
