package com.lucene.createindex.LuceneCreateIndex;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.store.LockObtainFailedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucene.createindex.DirectoryWatcher.WatchDirectory;
import com.lucene.createindex.DirectoryWatcher.WriteIndex;

/**
 * @author AR313567
 *
 */
@SpringBootApplication
public class LuceneCreateIndexApplication {
	final static Logger logger = Logger
			.getLogger(LuceneCreateIndexApplication.class);

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(LuceneCreateIndexApplication.class, args);
		WriteIndex writeIndex = new WriteIndex();
		String indexwriter = null;
		try {
			indexwriter = writeIndex.writeIndex();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("indexing first time" + e.toString());
		}
		logger.info("indexing first time -------" + indexwriter);
		WatchDirectory watchDirectory = new WatchDirectory();
		watchDirectory.watchService();

	}
}
