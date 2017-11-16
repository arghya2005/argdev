package com.lucene.search.resource;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lucene.search.index.LuceneSearch;

/**
 * @author AR313567 
 * rest controller.
 */
@RestController
@RequestMapping("/rest/search")
public class LuceneSearchController {
	final static Logger logger = Logger.getLogger(LuceneSearch.class);

	@RequestMapping(value = "/value", method = RequestMethod.POST)
	public ResponseEntity<?> searchName(
			@RequestParam("searchString") String text) {
		String response = "";
		try {

			response = LuceneSearch.SearchIndex(text);
		} catch (Exception e) {
			logger.error("Error code 110 : mislenious exception "
					+ e.toString());
			response = "Fail to start searching";
		}
		return ResponseEntity.ok(response);
	}

}
