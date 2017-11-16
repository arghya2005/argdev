package com.lucene.createindex.DirectoryWatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.lucene.createindex.utils.Utilfile;

@Configuration
public class WriteIndex {
	final static Logger logger = Logger.getLogger(WriteIndex.class);

	/**
	 * @return
	 * @throws LockObtainFailedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws AlreadyClosedException
	 *             this is o write index to the index folder. it takes files
	 *             from root folder and sub folders and indexes. it use lucene
	 *             api for indexing and TIKA api for parsing.
	 * 
	 */

	public String writeIndex() throws LockObtainFailedException,
			FileNotFoundException, IOException, AlreadyClosedException {
		IndexWriter writer = null;
		logger.info("inside index writing");
		try {
			FileInputStream fis = new FileInputStream("System.properties");
			ResourceBundle resourceBundle = new PropertyResourceBundle(fis);
			logger.info("Capturing Root folder and index older from resource buldle");
			String INDEX_DIRECTORY = resourceBundle.getString("ROOT_INDEX");
			File docs = new File(resourceBundle.getString("ROOT_FOLDER"));
			Utilfile fileUtil = new Utilfile();
			List<String> listFiles = new ArrayList<String>();
			File indexDir = new File(INDEX_DIRECTORY);
			if (docs.isDirectory() && docs.list().length == 0) {
				logger.error("ERROR code 105 : the root directory is empty");
			}
			Directory directory = FSDirectory.open(indexDir);// this directory
																// will contain
																// the indexes
			logger.info("Opening endex folder");
			Analyzer analyzer = new SimpleAnalyzer(Version.LUCENE_36);
			logger.info("Creating analyzer object");
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36,
					analyzer);

			writer = new IndexWriter(directory, conf);

			writer.deleteAll();
			listFiles = fileUtil.getAllFiles(docs);
			logger.info("geting all file from root ans sub folders");
			for (String listfileName : listFiles) {
				logger.info("listfileName" + listfileName);
				Metadata metadata = new Metadata();
				ContentHandler handler = new BodyContentHandler(-1);
				logger.info("Tika parsing handler created");
				ParseContext context = new ParseContext();
				Parser parser = new AutoDetectParser();
				InputStream stream = new FileInputStream(listfileName);
				try {
					parser.parse(stream, handler, metadata, context);
				} catch (TikaException e) {

					e.printStackTrace();
					logger.error("ERROR :" + e.toString());
				} catch (SAXException e) {
					e.printStackTrace();
					logger.error("ERROR :" + e.toString());
				} finally {
					fis.close();
				}
				stream.close();
				String text = handler.toString();
				String fileName = listfileName;

				Document doc = new Document();
				logger.info("creating document");
				Field contentField = new Field("text", text, Store.YES,
						Index.ANALYZED);

				doc.add(contentField);
				doc.add(new Field("file", fileName, Store.YES, Index.NO));
				logger.info("creating fields for document");
				writer.updateDocument(new Term("text", text), doc);
				logger.info("writing documnt on indexer");
			}
			writer.commit();
			writer.deleteUnusedFiles();
			writer.close();
		} catch (AlreadyClosedException e) {
			logger.info("This exception occurs if index writer is closed");

		} catch (LockObtainFailedException e) {
			logger.error("Error code 105 : Fail to obtain lock");

		} catch (FileNotFoundException e) {
			logger.error("Error code 101 : file not found" + e.toString());
			return "failure";
		} catch (Exception e) {
			logger.error("Error code 110 : mislenious exception "
					+ e.toString());
		} finally {
			logger.info("finally writer closing the index if previously not done due to exception.");
			if (writer != null) {
				writer.close();
			}

		}
		return "success";
	}
}