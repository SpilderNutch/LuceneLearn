package com.yutian.lucene.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.service.HotelService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	public static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	public static final String PATH = "/Users/yuouyang/javadev/hotelIndex";
	
	@Resource(name="hotelService")
	private HotelService hotelService;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response,String id){
		if(id==null || "".equals(id)){
			id = "23679";
		}
		//Hotel hotel = hotelService.selectByPrimaryKey(Integer.parseInt(id));
		
		//logger.info("hotel message:"+hotel.toString());
		
		return "/index.jsp";
	}
	
	@RequestMapping(value="/query")
	public @ResponseBody Map query(HttpServletRequest request,HttpServletResponse response,String id) throws IOException, ParseException{
		Map resultMap = Maps.newHashMap();
		List<Hotel> hotels = Lists.newArrayList();
		String name = request.getParameter("name");
		
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory = FSDirectory.open(Paths.get(PATH));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser("lkName", analyzer);
		Query query = parser.parse(name);
		ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
		//assertEquals(1, hits.length);
		// Iterate through the results:
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			logger.info("hitDoc:"+hitDoc);
			Hotel hotel = translateHitDocToHotel(hitDoc);
			hotels.add(hotel	);
			System.out.println("hitDoc.getFieldName:"+hitDoc.get("lkName"));
			//assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
		}
		ireader.close();
		
		resultMap.put("hotels",	hotels);
		return resultMap;
	}
	
	
	/**
	 * 
	 * @param hitDoc
	 * @return
	 */
	private Hotel translateHitDocToHotel(Document hitDoc) {
		Hotel hotel = new Hotel();
		hotel.setId(Integer.parseInt(hitDoc.get("id")));
		hotel.setLkName(hitDoc.get("lkName"));
		hotel.setLkSex(hitDoc.get("lkSex"));
		hotel.setLkIdCode(hitDoc.get("lkIdCode"));
		hotel.setLkAddress(hitDoc.get("lkAddress"));
		hotel.setLgHName(hitDoc.get("lgHName"));
		hotel.setLgHAddress(hitDoc.get("lgHAddress"));
		hotel.setLkNoroom(hitDoc.get("lkNoroom"));
		return hotel;
	}

	/** getter and setter methods**/
	public HotelService getHotelService() {
		return hotelService;
	}

	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	
}
