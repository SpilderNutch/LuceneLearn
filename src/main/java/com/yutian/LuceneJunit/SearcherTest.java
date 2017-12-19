package com.yutian.LuceneJunit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearcherTest {
	public static final Logger logger = LoggerFactory.getLogger(SearcherTest.class);
	
	public static final String PATH = "/Users/yuouyang/javadev/hotelIndex";

	@Test
	public void testSeacherLunceneFile() throws IOException, ParseException{
		
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory = FSDirectory.open(Paths.get(PATH));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser("lkName", analyzer);
		Query query = parser.parse("明");
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
	
	
}
