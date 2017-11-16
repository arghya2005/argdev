package com.lucene.createindex.DirectoryWatcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.store.LockObtainFailedException;
import org.springframework.stereotype.Component;

/**
 * @author AR313567 This is a watcher service which work on a root directory
 *         check any event like CREATE, MODIFY , DELETE happened or no. it will
 *         call write index if any event occurs.
 */

@Component
public class WatchDirectory {
	final static Logger logger = Logger.getLogger(WatchDirectory.class);

	static public void watchService() {

		WatchService watchService;
		WatchKey watchKey = null;
		Path directory = null;
		logger.info("Inside file watcher :");
		try {
			FileInputStream fis = new FileInputStream("System.properties");
			ResourceBundle resourceBundle = new PropertyResourceBundle(fis);
			logger.info("Capturing Root folder and index older from resource buldle");
			directory = Paths.get(resourceBundle.getString("ROOT_FOLDER"));

			watchService = FileSystems.getDefault().newWatchService();
			watchKey = directory.register(watchService,
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);
			logger.info("registering envents kind");
		} catch (FileNotFoundException e) {
			logger.error("Error code 101 : file not found" + e.toString());
		} catch (IOException e) {
			logger.error("Error code 109 : mislenious IO exception "
					+ e.toString());
		}
		while (true) {
			int counter = 0;
			for (WatchEvent<?> event : watchKey.pollEvents()) {
				logger.info("event" + event.kind());
				Path file = directory.resolve((Path) event.context());
				logger.info("file" + file);
				counter++;
			}
			if (counter > 0) {
				WriteIndex writeIndex = new WriteIndex();
				try {
					String Result = writeIndex.writeIndex();

					logger.info("-------" + Result + "----------");

				} catch (AlreadyClosedException e) {
					logger.info("This exception occurs if index writer is closed");

				} catch (LockObtainFailedException e) {
					logger.error("Error code 105 : Fail to obtain lock");

				} catch (FileNotFoundException e) {
					logger.error("Error code 101 : file not found"
							+ e.toString());

				} catch (Exception e) {
					logger.error("Error code 110 : mislenious exception "
							+ e.toString());
				}
				counter = 0;
			}

		}

	}

}
