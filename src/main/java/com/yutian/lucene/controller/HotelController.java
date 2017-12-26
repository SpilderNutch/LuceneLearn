package com.yutian.lucene.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yutian.lucene.entity.Hotel;
import com.yutian.lucene.service.HotelService;
import com.yutian.util.DateTool;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	public static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	public static final String PATH = "/Users/yuouyang/javadev/hotelIndex";
	private static Analyzer analyzer = new StandardAnalyzer();
	
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
		String name = request.getParameter("name");//name
		String idCard = request.getParameter("idCard");//idCard
		String lkNoroom = request.getParameter("lkNoroom");//lkNoroom
		String lkLtimeBefore = request.getParameter("lkLtimeBefore");//lkLtimeBefore
		String lkLtime = request.getParameter("lkLtime");//lkLtime
		String lkEtime = request.getParameter("lkEtime");//lkLtime
		String lkEtimeAfter = request.getParameter("lkEtimeAfter");//lkEtimeAfter
		
		Stopwatch stopwatch = Stopwatch.createStarted();
		Directory directory = FSDirectory.open(Paths.get(PATH));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		
		Query query = constrQuery(name,idCard,lkNoroom,lkLtimeBefore,lkLtime,lkEtime,lkEtimeAfter);
		
		logger.info("query.toString():	"+query.toString());
		
		ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
		//assertEquals(1, hits.length);
		// Iterate through the results:
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			//logger.info("hitDoc:"+hitDoc);
			Hotel hotel = translateHitDocToHotel(hitDoc);
			hotels.add(hotel	);
			//System.out.println("hitDoc.getFieldName:"+hitDoc.get("lkLtime"));
			//assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
		}
		ireader.close();
		directory.close();
		
		stopwatch.stop();
		
		resultMap.put("hotels",	hotels);
		resultMap.put("costTime",stopwatch.elapsed(TimeUnit.MILLISECONDS));//毫秒数
		return resultMap;
	}
	
	/**
	 * 查询不知名的小伙伴
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value="/queryPartner")
	public @ResponseBody Map queryPartner(HttpServletRequest request,HttpServletResponse response,String id) throws IOException, ParseException{
		Map resultMap = Maps.newHashMap();
		List<Hotel> hotels = Lists.newArrayList();
		String hotelId = request.getParameter("id");//
		int hotelIntId = Integer.parseInt(hotelId);//将hotelId转化为Id
		
		Stopwatch stopwatch = Stopwatch.createStarted();
		Directory directory = FSDirectory.open(Paths.get(PATH));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		Hotel hotel = hotelService.selectByPrimaryKey(hotelIntId);
		String message = "";
		boolean dateError = false;//inter compareTo leave
		if(hotel.getLkLtime()!=null && hotel.getLkEtime()!=null){
			if(hotel.getLkEtime().before(hotel.getLkLtime())){//leave before inter error
				message = "入住时间大于离开时间，数据异常，请忽略该条数据";
				dateError = true;
			}else if(((hotel.getLkEtime().getTime()-hotel.getLkLtime().getTime())/(1000*60*60*24*5))>1){//相隔天数>5
				message = "相隔天数大于5，数据异常，请忽略该条数据";
				dateError = true;
			}else{
				String lkLTime = DateTool.dateToString(new Date(hotel.getLkLtime().getTime()+1000*60*30), DateTool.dateTimeFormat);
				String lkETime = DateTool.dateToString(new Date(hotel.getLkEtime().getTime()-1000*60*30), DateTool.dateTimeFormat);
				Date lkLTimeBefore = new Date(hotel.getLkLtime().getTime()-1000*60*30);//向前推迟半个小时
				Date lkETimeAfter = new Date(hotel.getLkEtime().getTime()+1000*60*30);//向后推迟半个小时 
				String lkLTimeBeforeStr = DateTool.dateToString(lkLTimeBefore, DateTool.dateTimeFormat);
				String lkETimeAfterStr = DateTool.dateToString(lkETimeAfter, DateTool.dateTimeFormat);
				Query query = constrParnterQuery(hotel.getLgHName(),hotel.getLkNoroom(),lkLTimeBeforeStr,lkLTime,lkETime,lkETimeAfterStr);
				
				logger.info("query.toString():	"+query.toString());
				
				ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
				//assertEquals(1, hits.length);
				for (int i = 0; i < hits.length; i++) {
					Document hitDoc = isearcher.doc(hits[i].doc);
					logger.info("hitDoc:"+hitDoc);
					Hotel result = translateHitDocToHotel(hitDoc);
					if(hotelIntId!=result.getId()){//剔除自己本身这条数据
						hotels.add(result);
					}
				}
				ireader.close();
				directory.close();
			}
		}else {
			message = "入住时间、离开时间存在空值，数据异常，请忽略该条数据";
			dateError = true;
		}
		
		stopwatch.stop();
		resultMap.put("dateError",dateError);
		resultMap.put("message", message);
		resultMap.put("hotels",	hotels);
		resultMap.put("costTime",stopwatch.elapsed(TimeUnit.MILLISECONDS));//毫秒数
		return resultMap;
	}
	
	/**
	 * 根据相应的数据，构建查询语句
	 * @param name
	 * @param idCard
	 * @param lkNoroom
	 * @param lkLtime
	 * @param lkEtime
	 * @return
	 */
	private Query constrQuery(String name, String idCard, String lkNoroom, String lkltimeBefore,String lkLtime, String lkEtime,String lkEtimeAfter) {
		Query nameQuery = null;
		if(!Strings.isNullOrEmpty(name)){
			nameQuery = new TermQuery(new Term("lkName", name));
		}
		Query idCardQuery = null;
		if(!Strings.isNullOrEmpty(idCard)){
			idCardQuery = new TermQuery(new Term("lkIdCode", idCard));
		}
		Query lkNoroomQuery = null;
		if(!Strings.isNullOrEmpty(lkNoroom)){
			lkNoroomQuery = new TermQuery(new Term("lkNoroom", lkNoroom));
		}
		TermRangeQuery lkLtimeQuery = null;
		TermRangeQuery lkEtimeQuery = null;
		if(!Strings.isNullOrEmpty(lkLtime) && !Strings.isNullOrEmpty(lkltimeBefore)){
			lkLtimeQuery = new TermRangeQuery("lkLtime", new BytesRef(lkltimeBefore),
					new BytesRef(lkLtime), true, true);
		}
		if(!Strings.isNullOrEmpty(lkEtime)&&!Strings.isNullOrEmpty(lkEtimeAfter)){
			lkEtimeQuery = new TermRangeQuery("lkEtime", new BytesRef(lkEtime),
					new BytesRef(lkEtimeAfter), true, true);
		}
		
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		if(nameQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(nameQuery, Occur.SHOULD);
			booleanQuery.add(nameClasuse);
		}
		if(idCardQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(idCardQuery, Occur.MUST);
			booleanQuery.add(nameClasuse);
		}
		if(lkNoroomQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(lkNoroomQuery, Occur.MUST);
			booleanQuery.add(nameClasuse);
		}
		if(lkLtimeQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(lkLtimeQuery, Occur.MUST);
			booleanQuery.add(nameClasuse);
		}
		if(lkEtimeQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(lkEtimeQuery, Occur.MUST);
			booleanQuery.add(nameClasuse);
		}
		
		
		return booleanQuery.build();
	}
	
	/**
	 * 根据相应的数据，构建Parnter查询语句
	 * @param name
	 * @param idCard
	 * @param lkNoroom
	 * @param lkLtime
	 * @param lkEtime
	 * @return
	 */
	private Query constrParnterQuery(String LgHName,String lkNoroom,String lkLtimeBefore, String lkLtime,  String lkEtime, String lkEtimeAfter) {
		
		Query lkNoroomQuery = null;
		if(!Strings.isNullOrEmpty(lkNoroom)){
			lkNoroomQuery = new TermQuery(new Term("lkNoroom", lkNoroom));
		}
		/**
		Query LgHNameQuery = null;
		if(!Strings.isNullOrEmpty(LgHName)){
			LgHNameQuery = new TermQuery(new Term("lgHName", LgHName));
		}**/
		TermRangeQuery lkLtimeQuery = null;
		TermRangeQuery lkEtimeQuery = null;
		if(!Strings.isNullOrEmpty(lkLtime)){
			lkLtimeQuery = new TermRangeQuery("lkLtime", new BytesRef(lkLtimeBefore),
					new BytesRef(lkLtime), true, true);
		}
		if(!Strings.isNullOrEmpty(lkEtime)){
			lkEtimeQuery = new TermRangeQuery("lkEtime", new BytesRef(lkEtime),
					new BytesRef(lkEtimeAfter), true, true);
		}
		
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		if(lkNoroomQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(lkNoroomQuery, Occur.MUST);
			booleanQuery.add(nameClasuse);
		}
//		if(LgHNameQuery!=null){
//			BooleanClause nameClasuse = new BooleanClause(LgHNameQuery, Occur.MUST);
//			booleanQuery.add(nameClasuse);
//		}
		if(lkLtimeQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(lkLtimeQuery, Occur.MUST);
			booleanQuery.add(nameClasuse);
		}
		if(lkEtimeQuery!=null){
			BooleanClause nameClasuse = new BooleanClause(lkEtimeQuery, Occur.MUST);
			booleanQuery.add(nameClasuse);
		}
		
		
		return booleanQuery.build();
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
		hotel.setLkLtime(DateTool.stringToDate(hitDoc.get("lkLtime"), DateTool.dateTimeFormat));
		hotel.setLkEtime(DateTool.stringToDate(hitDoc.get("lkEtime"), DateTool.dateTimeFormat));
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
