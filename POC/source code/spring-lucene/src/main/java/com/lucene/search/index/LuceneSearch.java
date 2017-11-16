package com.lucene.search.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.springframework.stereotype.Service;

/**
 * @author AR313567
 *
 */
public class LuceneSearch {
	final static Logger logger = Logger.getLogger(LuceneSearch.class);

	/**
	 * @param searchString
	 * @return
	 * @throws IOException
	 *             it takes the search string and search for the phrase on index
	 *             folder
	 */
	public static String SearchIndex(String searchString) throws IOException {
		String returnvalue = "";
		FileInputStream fis = null;
		logger.info("inside Lucene search");
		try {
			fis = new FileInputStream("System.properties");
			ResourceBundle resourceBundle = new PropertyResourceBundle(fis);
			File indexDir = new File(resourceBundle.getString("ROOT_INDEX"));
			logger.info("Getting root index folder");
			Directory index = FSDirectory.open(indexDir);

			PhraseQuery query = new PhraseQuery();//

			String[] words = searchString.split(" ");
			for (String word : words) {
				query.add(new Term("text", word.toLowerCase()));
			}
			logger.info("creating Phrase Query");
			int hitsPerPage = 10;
			IndexReader reader = IndexReader.open(index);
			logger.info("creating inder object");
			IndexSearcher searcher = new IndexSearcher(reader);
			logger.info("creating inder searcher object");
			ScoreDoc[] hits = searcher.search(query, hitsPerPage).scoreDocs;

			String filenamevalue = "";
			logger.info("Number of hit" + hits.length);
			if (hits.length == 0) {
				logger.error("ERROR code 100: No file found for the string searched");
			}
			for (ScoreDoc hit : hits) {
				Document doc = reader.document(hit.doc);
				filenamevalue = doc.get("file");
				returnvalue = returnvalue + filenamevalue + '\n';
				logger.info("name of the files " + returnvalue);

			}
			// searcher.close();
			reader.close();
			index.close();

		} catch (LockObtainFailedException e) {
			logger.error("Error code 105 : Fail to obtain lock");

		} catch (FileNotFoundException e) {
			logger.error("Error code 101 : file not found" + e.toString());

		} catch (IOException e) {
			logger.error("Error code 109 : mislenious exception "
					+ e.toString());
			return "ERROR :" + e.toString();
		} catch (Exception e) {
			logger.error("Error code 110 : mislenious exception "
					+ e.toString());
			return "ERROR :" + e.toString();
		} finally {
			fis.close();
		}
		return returnvalue;
	}
}
