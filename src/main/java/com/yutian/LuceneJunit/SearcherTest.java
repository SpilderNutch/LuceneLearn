package com.yutian.LuceneJunit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.function.valuesource.RangeMapFloatFunction;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import org.apache.lucene.queryparser.xml.builders.RangeQueryBuilder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearcherTest {
	public static final Logger logger = LoggerFactory.getLogger(SearcherTest.class);
	
	public static final String PATH = "/Users/yuouyang/javadev/hotelIndex";

	/**
	 * 根据用户名对索引数据进行查询
	 */
	@Test
	public void testSeacherLunceneFile() throws IOException, ParseException{
		
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory = FSDirectory.open(Paths.get(PATH));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser("lkName", analyzer);
		Query query = parser.parse("明");
		
		logger.info("queryToString:"+query.toString());
		
		ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
		//assertEquals(1, hits.length);
		// Iterate through the results:
		if(hits.length > 0){
			logger.info("Congruations ,It has more than one records。");
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println("hitDoc.getFieldName:"+hitDoc.get("lkName"));
			}
		}
		ireader.close();
		directory.close();
	}

	
	/***
	 * 根据idCard进行查询
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testSeacherIdCards() throws IOException, ParseException{
		String idCard = "440301199502015110";
		
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory = FSDirectory.open(Paths.get(PATH));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser("lkIdCode", analyzer);
		Query query = parser.parse(idCard);
		
		logger.info("queryToString:"+query.toString());
		
		ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
		//assertEquals(1, hits.length);
		// Iterate through the results:
		if(hits.length > 0){
			logger.info("Congruations ,It has more than one records。");
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println("hitDoc.getFieldName:"+hitDoc.get("lkName"));
			}
		}else{
			logger.info("Sorry la ,There is no record.Try another key words");
		}
		ireader.close();
		directory.close();
	}
	
	/***
	 * 根据入住范围进行查询
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testSeacherRangeTime() throws IOException, ParseException{
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory = FSDirectory.open(Paths.get(PATH));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		//RangeQueryBuilder builder =
		TermRangeQuery termRangeQuery = new TermRangeQuery("lkLtime", new BytesRef("2010-03-17 00:01:00"),
				new BytesRef("2010-03-17 00:10:00"), true, true);
		//QueryParser parser = new QueryParser("lkIdCode", analyzer);
		//Query query = parser.parse(idCard);
		
		logger.info("queryToString:"+termRangeQuery.toString());
		
		ScoreDoc[] hits = isearcher.search(termRangeQuery, 1000).scoreDocs;
		//assertEquals(1, hits.length);
		// Iterate through the results:
		if(hits.length > 0){
			logger.info("Congruations ,It has more than one records。");
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println("hitDoc.getFieldName:"+hitDoc);
			}
		}else{
			logger.info("Sorry la ,There is no record.Try another key words");
		}
		ireader.close();
		directory.close();
	}
	
	
	@Test
	public void testLuceneDelete() throws IOException, ParseException{
		Analyzer analyzer = new StandardAnalyzer();
		FSDirectory fsDirectory = null;
		IndexWriter indexWriter = null;
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
		indexWriter.deleteAll();
		fsDirectory.close();
	}
	
	
}
