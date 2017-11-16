package com.lucene.createindex.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author AR313567 This class is to search files from root and sub folders
 *
 */
public class Utilfile {
	final static Logger logger = Logger.getLogger(Utilfile.class);

	public List<String> getAllFiles(File docs) {
		// TODO Auto-generated method stub
		logger.info("inside get all files");
		List<String> list = new ArrayList<String>();
		File currentDir = new File(docs.getAbsolutePath()); // current root
															// directory
		List<String> filelist = new ArrayList<String>();
		filelist = displayDirectoryContents(currentDir, list);
		return filelist;
	}

	public List<String> displayDirectoryContents(File dir, List<String> list) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					displayDirectoryContents(file, list);
				} else {
					list.add(file.getCanonicalPath());
				}
			}
			return list;
		} catch (IOException e) {
			logger.error("Error code 109 : mislenious IO exception "
					+ e.toString());
			return list;
		}
	}

}
